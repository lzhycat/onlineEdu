package cn.hycat.service.statistics.ucenter.service.impl;

import cn.hycat.service.statistics.ucenter.domain.vo.LoginVo;
import cn.hycat.service.statistics.ucenter.domain.vo.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import cn.hycat.service.statistics.service.util.enums.ResultCodeEnum;
import cn.hycat.service.statistics.ucenter.mapper.MemberMapper;
import cn.hycat.service.statistics.service.base.dto.MemberDto;
import cn.hycat.service.statistics.service.base.exception.SysException;
import cn.hycat.service.statistics.ucenter.domain.entity.Member;
import cn.hycat.service.statistics.ucenter.service.MemberService;
import cn.hycat.service.statistics.service.util.util.FormUtils;
import cn.hycat.service.statistics.service.util.util.JwtInfo;
import cn.hycat.service.statistics.service.util.util.JwtUtils;
import cn.hycat.service.statistics.service.util.util.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
* @author admin
* @description 针对表【ucenter_member(会员表)的数据库操作Service实现
* @createDate 2022-03-27 12:44:17
*/
@Service
@Slf4j
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member>
    implements MemberService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public void register(RegisterVo registerVo) {
        String nickName = registerVo.getNickname();
        String passWord = registerVo.getPassword();
        String checkCode = registerVo.getCode();
        String mobile = registerVo.getMobile();

        //校验非空
        if(Objects.isNull(nickName)
        || Objects.isNull(passWord)
        || Objects.isNull(mobile)
        || Objects.isNull(checkCode))
            throw new SysException(ResultCodeEnum.PARAMETER_EMPTY);

        //校验手机号适配性
        String code = (String) redisTemplate.opsForValue().get(mobile);
        if(!FormUtils.isMobile(mobile) || Objects.isNull(code))
            throw new SysException(ResultCodeEnum.LOGIN_PHONE_ERROR);

        //校验验证码
        if(!code.equals(checkCode))
            throw new SysException(ResultCodeEnum.CODE_ERROR);

        //校验手机号尚未注册
        QueryWrapper<Member> wrapper = new QueryWrapper();
        wrapper.eq("mobile", mobile);
        Integer integer = getBaseMapper().selectCount(wrapper);

        if(integer != 0)
            throw new SysException(ResultCodeEnum.REGISTER_MOBLE_ERROR);

        Member member = new Member();
        member.setMobile(mobile);
        member.setPassword(MD5.encrypt(passWord));
        member.setNickname(nickName);
        member.setAvatar("https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        save(member);
    }

    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        //非空判断
        if(Objects.isNull(mobile) || Objects.isNull(password))
            throw new SysException(ResultCodeEnum.PARAMETER_EMPTY);

        //手机合法性
        if(!FormUtils.isMobile(mobile))
            throw new SysException(ResultCodeEnum.LOGIN_MOBILE_ERROR);

        //校验手机号
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Member member = baseMapper.selectOne(queryWrapper);
        if(member == null){
            throw new SysException(ResultCodeEnum.LOGIN_MOBLE_ERROR);
        }

        //校验密码
        if(!MD5.encrypt(password).equals(member.getPassword())){
            throw new SysException(ResultCodeEnum.LOGIN_PASSWORD_ERROR);
        }

        //检验用户是否被禁用
        if(member.getIsDisabled() == 1){
            throw new SysException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }

        //生成token
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(member.getId());
        jwtInfo.setNickname(member.getNickname());
        jwtInfo.setAvatar(member.getAvatar());
        String token = JwtUtils.getJwtToken(jwtInfo, 1800);

        return token;
    }

    @Override
    public Member getByOpenId(String openId) {
        QueryWrapper<Member> wrapper = new QueryWrapper();
        wrapper.eq("openid", openId);
        Member member = getBaseMapper().selectOne(wrapper);
        return member;
    }

    @Override
    public MemberDto getMemberDtoById(String memberId) {
        Member member = getBaseMapper().selectById(memberId);
        MemberDto memberDto = new MemberDto();
        BeanUtils.copyProperties(member, memberDto);
        return memberDto;
    }

    @Override
    public Integer registerQuery(String day) {
        return memberMapper.registerQuery(day);
    }
}




