package cn.hycat.service.statistics.service.cms.controller.api;

import cn.hycat.service.statistics.service.cms.domain.Ad;
import cn.hycat.service.statistics.service.cms.service.AdService;
import cn.hycat.service.statistics.service.util.entity.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@RestController
@RequestMapping("/api/cms/ad")
@Api(tags = "首页")
@Slf4j
public class ApiAdController {

    @Resource
    private AdService adService;

    @GetMapping("listByTypeId/{typeId}")
    public ResponseResult listByAdType(@ApiParam("广告类型") @PathVariable String typeId) {
        List<Ad> lists = adService.listByAdType(typeId);

        return ResponseResult.ok().data("elements", lists);
    }
}
