package cn.hycat.service.cms.controller.admin;


import cn.hycat.service.cms.domain.Ad;
import cn.hycat.service.cms.domain.vo.AdVo;
import cn.hycat.service.cms.service.AdService;
import cn.hycat.service.util.entity.ResponseResult;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 广告推荐 前端控制器
 * </p>
 *
 * @author hy
 * @since 2022-04-20
 */
@Api(tags = "广告推荐管理")
@RestController
@RequestMapping("/admin/cms/ad")
@Slf4j
public class AdController {

    @Resource
    private AdService adService;

    @ApiOperation("新增推荐")
    @PostMapping("save")
    public ResponseResult save(@ApiParam(value = "推荐对象", required = true) @RequestBody Ad ad) {

        boolean result = adService.save(ad);
        if (result) {
            return ResponseResult.ok().message("保存成功");
        } else {
            return ResponseResult.error().message("保存失败");
        }
    }

    @ApiOperation("更新推荐")
    @PutMapping("update")
    public ResponseResult updateById(@ApiParam(value = "讲师推荐", required = true) @RequestBody Ad ad) {
        boolean result = adService.updateById(ad);
        if (result) {
            return ResponseResult.ok().message("修改成功");
        } else {
            return ResponseResult.error().message("数据不存在");
        }
    }

    @ApiOperation("根据id获取推荐信息")
    @GetMapping("get/{id}")
    public ResponseResult getById(@ApiParam(value = "推荐ID", required = true) @PathVariable String id) {
        Ad ad = adService.getById(id);
        if (ad != null) {
            return ResponseResult.ok().data("item", ad);
        } else {
            return ResponseResult.error().message("数据不存在");
        }
    }

    @ApiOperation("推荐分页列表")
    @GetMapping("list/{page}/{limit}")
    public ResponseResult listPage(@ApiParam(value = "当前页码", required = true) @PathVariable Long page,
                      @ApiParam(value = "每页记录数", required = true) @PathVariable Long limit) {

        Page<AdVo> pageModel = adService.selectPage(page, limit);
        List<AdVo> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return ResponseResult.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "根据ID删除推荐")
    @DeleteMapping("remove/{id}")
    public ResponseResult removeById(@ApiParam(value = "推荐ID", required = true) @PathVariable String id) {

        //删除图片
        adService.removeAdImageById(id);

        //删除推荐
        boolean result = adService.removeById(id);
        if (result) {
            return ResponseResult.ok().message("删除成功");
        } else {
            return ResponseResult.error().message("数据不存在");
        }
    }
}

