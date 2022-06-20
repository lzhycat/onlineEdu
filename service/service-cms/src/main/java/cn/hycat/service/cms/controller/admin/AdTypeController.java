package cn.hycat.service.cms.controller.admin;


import cn.hycat.service.cms.domain.AdType;
import cn.hycat.service.cms.service.AdTypeService;
import cn.hycat.service.util.entity.ResponseResult;
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
 * 推荐位 前端控制器
 * </p>
 *
 * @author hy
 * @since 2022-04-20
 */
@Api(tags = "推荐位管理")
@RestController
@RequestMapping("/admin/cms/ad-type")
@Slf4j
public class AdTypeController {

    @Resource
    private AdTypeService adTypeService;

    @ApiOperation("所有推荐类别列表")
    @GetMapping("list")
    public ResponseResult listAll() {
        List<AdType> list = adTypeService.list();
        return ResponseResult.ok().data("items", list);
    }

    @ApiOperation("推荐类别分页列表")
    @GetMapping("list/{page}/{limit}")
    public ResponseResult listPage(@ApiParam(value = "当前页码", required = true) @PathVariable Long page,
                      @ApiParam(value = "每页记录数", required = true) @PathVariable Long limit) {

        Page<AdType> pageParam = new Page<>(page, limit);
        Page<AdType> pageModel = adTypeService.page(pageParam);
        List<AdType> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return ResponseResult.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "根据ID删除推荐类别")
    @DeleteMapping("remove/{id}")
    public ResponseResult removeById(@ApiParam(value = "推荐类别ID", required = true) @PathVariable String id) {

        boolean result = adTypeService.removeById(id);
        if (result) {
            return ResponseResult.ok().message("删除成功");
        } else {
            return ResponseResult.error().message("数据不存在");
        }
    }

    @ApiOperation("新增推荐类别")
    @PostMapping("save")
    public ResponseResult save(@ApiParam(value = "推荐类别对象", required = true) @RequestBody AdType adType) {

        boolean result = adTypeService.save(adType);
        if (result) {
            return ResponseResult.ok().message("保存成功");
        } else {
            return ResponseResult.error().message("保存失败");
        }
    }

    @ApiOperation("更新推荐类别")
    @PutMapping("update")
    public ResponseResult updateById(@ApiParam(value = "讲师推荐类别", required = true) @RequestBody AdType adType) {
        boolean result = adTypeService.updateById(adType);
        if (result) {
            return ResponseResult.ok().message("修改成功");
        } else {
            return ResponseResult.error().message("数据不存在");
        }
    }

    @ApiOperation("根据id获取推荐类别信息")
    @GetMapping("get/{id}")
    public ResponseResult getById(@ApiParam(value = "推荐类别ID", required = true) @PathVariable String id) {
        AdType adType = adTypeService.getById(id);
        if (adType != null) {
            return ResponseResult.ok().data("item", adType);
        } else {
            return ResponseResult.error().message("数据不存在");
        }
    }
}
