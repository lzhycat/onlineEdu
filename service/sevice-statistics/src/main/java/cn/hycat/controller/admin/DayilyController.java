package cn.hycat.controller.admin;

import cn.hycat.service.DayilyService;
import cn.hycat.service.util.entity.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : lzhycat
 * @date : 2022-07-06 17:17
 */
@Api(tags = "后台统计")
@RestController
@RequestMapping("/admin/statistics")
public class DayilyController {

    @Autowired
    private DayilyService dayilyService;

    @GetMapping("dayilyInformation")
    @ApiOperation("每日数据统计")
    public ResponseResult DayilyInformationQuery() {
        return dayilyService.creat();
    }
}
