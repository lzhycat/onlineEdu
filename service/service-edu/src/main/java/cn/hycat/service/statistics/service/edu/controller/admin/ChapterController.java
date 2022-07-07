package cn.hycat.service.statistics.service.edu.controller.admin;


import cn.hycat.service.statistics.service.edu.domain.entity.Chapter;
import cn.hycat.service.statistics.service.util.entity.ResponseResult;
import cn.hycat.service.statistics.service.edu.domain.vo.ChapterVo;
import cn.hycat.service.statistics.service.edu.service.ChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author hy
 * @since 2022-03-08
 */
@Api(tags = "章节管理")
@RestController
@RequestMapping("/admin/edu/chapter")
@Slf4j
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    @ApiOperation("新增章节")
    @PostMapping("/save")
    public ResponseResult save(
            @ApiParam(value="章节对象", required = true)
            @RequestBody Chapter chapter){
        boolean result = chapterService.save(chapter);
        if (result) {
            return ResponseResult.ok().message("保存成功");
        } else {
            return ResponseResult.error().message("保存失败111");
        }
    }

    @ApiOperation("根据id查询章节")
    @GetMapping("get/{id}")
    public ResponseResult getById(
            @ApiParam(value="章节id", required = true)
            @PathVariable String id){

        Chapter chapter = chapterService.getById(id);
        if (chapter != null) {
            return ResponseResult.ok().data("element", chapter);
        } else {
            return ResponseResult.error().message("数据不存在");
        }
    }

    @ApiOperation("根据id修改章节")
    @PutMapping("update")
    public ResponseResult updateById(
            @ApiParam(value="章节对象", required = true)
            @RequestBody Chapter chapter){

        boolean result = chapterService.updateById(chapter);
        if (result) {
            return ResponseResult.ok().message("修改成功");
        } else {
            return ResponseResult.error().message("数据不存在");
        }
    }

    @ApiOperation("根据ID删除章节")
    @DeleteMapping("remove/{id}")
    public ResponseResult removeById(
            @ApiParam(value = "章节ID", required = true)
            @PathVariable String id){

        //TODO 删除视频：VOD
        //在此处调用vod中的删除视频文件的接口

        boolean result = chapterService.removeChapterById(id);
        if (result) {
            return ResponseResult.ok().message("删除成功");
        } else {
            return ResponseResult.error().message("数据不存在");
        }
    }

    @ApiOperation("嵌套章节数据列表")
    @GetMapping("nested-list/{courseId}")
    public ResponseResult nestedListByCourseId(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String courseId){

        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);
        return ResponseResult.ok().data("element", chapterVoList);
    }
}

