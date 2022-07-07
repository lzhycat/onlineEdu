package cn.hycat.service.statistics.ucenter.controller.admin;

import cn.hycat.service.statistics.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : lzhycat
 * @date : 2022-07-07 11:05
 */
@Api(tags = "会员管理")
@RestController
@RequestMapping("/admin/ucenter/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/register/{day}")
    @ApiOperation("统计注册人数")
    public Integer registerQuery(@PathVariable String day) {
        return memberService.registerQuery(day);
    }
}
