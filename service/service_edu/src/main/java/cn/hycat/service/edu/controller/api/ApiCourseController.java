package cn.hycat.service.edu.controller.api;

import cn.hycat.service.edu.domain.entity.Course;
import cn.hycat.service.util.entity.ResponseResult;
import cn.hycat.service.base.dto.CourseDto;
import cn.hycat.service.edu.domain.search.WebCourseSearch;
import cn.hycat.service.edu.domain.vo.ChapterVo;
import cn.hycat.service.edu.domain.vo.WebCourseVo;
import cn.hycat.service.edu.service.ChapterService;
import cn.hycat.service.edu.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Api(tags="课程")
@RestController
@RequestMapping("/api/edu/course")
public class ApiCourseController {

    @Resource
    private CourseService courseService;

    @Resource
    private ChapterService chapterService;

    @ApiOperation("课程列表")
    @GetMapping("list")
    public ResponseResult list(
            @ApiParam(value = "查询对象", required = false)
                    WebCourseSearch webCourseQueryVo){
        List<Course> courseList = courseService.webSelectList(webCourseQueryVo);
        return  ResponseResult.ok().data("courseList", courseList);
    }

    @ApiOperation("根据ID查询课程")
    @GetMapping("get/{courseId}")
    public ResponseResult getById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String courseId){

        //查询课程信息和讲师信息
        WebCourseVo webCourseVo = courseService.selectWebCourseVoById(courseId);

        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);

        return ResponseResult.ok().data("course", webCourseVo).data("chapterVoList", chapterVoList);
    }

    @ApiOperation("返回课程dto")
    @GetMapping("inner/get-course-dto/{courseId}")
    public CourseDto getCourseDtoByCourseId(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String courseId){
        CourseDto courseDto = courseService.getCourseDtoByCourseId(courseId);
        return courseDto;
    }

//    @ApiOperation("根据title查询课程")
//    @GetMapping("get/{title}")
//    public ResponseResult getByTitle(
//            @ApiParam(value = "课程名", required = true)
//            @PathVariable String title){
//
//        //查询课程信息和讲师信息
//        WebCourseVo webCourseVo = courseService.selectWebCourseVoById(courseId);
//
//        //查询当前课程的章节信息
//        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);
//
//        return ResponseResult.ok().data("course", webCourseVo).data("chapterVoList", chapterVoList);
//    }
}
