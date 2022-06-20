package cn.hycat.service.edu.controller.admin;


import cn.hycat.service.edu.domain.entity.Video;
import cn.hycat.service.util.entity.ResponseResult;
import cn.hycat.service.edu.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author hy
 * @since 2022-03-08
 */
@Api(tags = "课时管理")
@RestController
@RequestMapping("/admin/edu/video")
@Slf4j
public class VideoController {

    @Resource
    private VideoService videoService;

    @ApiOperation("新增课时")
    @PostMapping("/save")
    public ResponseResult save(
            @ApiParam(value="课时对象", required = true)
            @RequestBody Video video){
        boolean result = videoService.save(video);
        if (result) {
            return ResponseResult.ok().message("保存成功");
        } else {
            return ResponseResult.error().message("保存失败");
        }
    }

    @ApiOperation("根据id查询课时")
    @GetMapping("get/{id}")
    public ResponseResult getById(
            @ApiParam(value="课时id", required = true)
            @PathVariable String id){

        Video video = videoService.getById(id);
        if (video != null) {
            return ResponseResult.ok().data("element", video);
        } else {
            return ResponseResult.error().message("数据不存在");
        }
    }

    @ApiOperation("根据id修改课时")
    @PutMapping("update")
    public ResponseResult updateById(
            @ApiParam(value="课时对象", required = true)
            @RequestBody Video video){

        boolean result = videoService.updateById(video);
        if (result) {
            return ResponseResult.ok().message("修改成功");
        } else {
            return ResponseResult.error().message("数据不存在");
        }
    }

    @ApiOperation("根据ID删除课时")
    @DeleteMapping("remove/{id}")
    public ResponseResult removeById(
            @ApiParam(value = "课时ID", required = true)
            @PathVariable String id){

        //TODO 删除视频：VOD
        //在此处调用vod中的删除视频文件的接口

        boolean result = videoService.removeById(id);
        if (result) {
            return ResponseResult.ok().message("删除成功");
        } else {
            return ResponseResult.error().message("数据不存在");
        }
    }
}
