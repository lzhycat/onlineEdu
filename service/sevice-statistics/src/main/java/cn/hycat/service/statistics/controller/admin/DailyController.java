package cn.hycat.service.statistics.controller.admin;

import cn.hycat.service.statistics.service.DailyService;
import cn.hycat.service.statistics.service.util.entity.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author : lzhycat
 * @date : 2022-07-06 17:17
 */
@Api(tags = "后台统计")
@RestController
@RequestMapping("/admin/statistics")
public class DailyController {

    @Resource
    private DailyService dailyService;

    @PostMapping("dailyRegister")
    @ApiOperation("当日注册数据统计")
    public ResponseResult DailyInfoQuery(@PathVariable String day) {
        return dailyService.dailyInfo(day);
    }
}
