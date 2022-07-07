package cn.hycat.service.statistics.service.edu.controller.api;

import cn.hycat.service.statistics.service.edu.domain.entity.Course;
import cn.hycat.service.statistics.service.edu.domain.entity.Teacher;
import cn.hycat.service.statistics.service.edu.service.CourseService;
import cn.hycat.service.statistics.service.edu.service.TeacherService;
import cn.hycat.service.statistics.service.util.entity.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@RestController
@RequestMapping("/api/edu/index")
@Api(tags = "首页")
@Slf4j
public class ApiIndexController {

    @Resource
    private TeacherService teacherService;

    @Resource
    private CourseService courseService;

    @ApiOperation("热门课程和讲师列表")
    @GetMapping()
    public ResponseResult index(){

        //获取首页最热门前8条课程数据
        List<Course> courseList = courseService.selectHotCourse();
        //获取首页推荐前4条讲师数据
        List<Teacher> teacherList = teacherService.selectHotTeacher();

        return ResponseResult.ok().data("courseList", courseList).data("teacherList", teacherList);
    }
}
