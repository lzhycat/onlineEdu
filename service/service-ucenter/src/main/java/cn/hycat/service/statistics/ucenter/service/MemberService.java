package cn.hycat.service.statistics.ucenter.service;

import cn.hycat.service.statistics.ucenter.domain.entity.Member;
import cn.hycat.service.statistics.ucenter.domain.vo.LoginVo;
import cn.hycat.service.statistics.ucenter.domain.vo.RegisterVo;
import cn.hycat.service.statistics.service.base.dto.MemberDto;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author admin
* @description 针对表【ucenter_member(会员表)】的数据库操作Service
* @createDate 2022-03-27 12:44:17
*/
public interface MemberService extends IService<Member> {

    void register(RegisterVo registerVo);

    String login(LoginVo loginVo);

    Member getByOpenId(String openId);

    MemberDto getMemberDtoById(String memberId);

    Integer registerQuery(String day);
}
