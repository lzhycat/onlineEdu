package cn.hycat.service.edu.controller.api;

import cn.hycat.service.util.entity.ResponseResult;
import cn.hycat.service.edu.domain.entity.Teacher;
import cn.hycat.service.edu.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Api(tags="讲师")
@RestController
@RequestMapping("/api/edu/teacher")
public class ApiTeacherController {

    @Resource
    private TeacherService teacherService;

    @ApiOperation(value="所有讲师列表")
    @GetMapping("/list")
    public ResponseResult listAll(){
        List<Teacher> list = teacherService.list(null);
        return ResponseResult.ok().data("element", list).message("获取讲师列表成功");
    }

    @ApiOperation(value = "获取讲师和讲师课程信息")
    @GetMapping("/getTeacherAndCourses/{id}")
    public ResponseResult getTeacherAndCourses(@ApiParam(value = "讲师id",required = true)
                                               @PathVariable String id) {

       Map<String,Object> teacherAndCourses = teacherService.getTeacherAndCourses(id);
        return ResponseResult.ok().data("teacherAndCourses",teacherAndCourses);
    }
}
