package cn.hycat.service.ucenter.controller.api;

import cn.hycat.service.ucenter.domain.vo.LoginVo;
import cn.hycat.service.ucenter.domain.vo.RegisterVo;
import cn.hycat.service.util.entity.ResponseResult;
import cn.hycat.service.util.enums.ResultCodeEnum;
import cn.hycat.service.ucenter.service.MemberService;
import cn.hycat.service.base.dto.MemberDto;
import cn.hycat.service.base.exception.SysException;
import cn.hycat.service.util.util.JwtInfo;
import cn.hycat.service.util.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Api(tags = "前台会员")
@RestController
@RequestMapping("/api/ucenter/member")
public class ApiMemberController {

    @Resource
    private MemberService memberService;


    @ApiOperation("用户注册")
    @PostMapping("register")
    public ResponseResult register(@ApiParam(value = "注册表单")
                                   @RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);

        return ResponseResult.ok().message("注册成功");
    }

    @ApiOperation("用户登录")
    @PostMapping("login")
    public ResponseResult login(@ApiParam(value = "登录表单")
                                   @RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);

        return ResponseResult.ok().message("登录成功").data("token",token);
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("get-login-info")
    public ResponseResult getLoginInfo(HttpServletRequest request){

        try{
            JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
            return ResponseResult.ok().data("userInfo", jwtInfo);
        }catch (Exception e){
            throw new SysException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }
    }

    @ApiOperation(value = "返回会员dto")
    @GetMapping("inner/get-member-dto/{memberId}")
    public MemberDto getMemberDtoById(
            @ApiParam(value = "会员ID", required = true)
            @PathVariable String memberId) {
        MemberDto memberDto = memberService.getMemberDtoById(memberId);
        return memberDto;
    }
}
