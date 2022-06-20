package cn.hycat.service.edu.controller.admin;


import cn.hycat.service.edu.domain.entity.Course;
import cn.hycat.service.util.entity.ResponseResult;
import cn.hycat.service.edu.feign.OssFeignService;
import cn.hycat.service.edu.domain.vo.CourseFrom;
import cn.hycat.service.edu.domain.vo.CourseListVo;
import cn.hycat.service.edu.domain.search.CourseSearch;
import cn.hycat.service.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(tags = "课程管理")
@RestController
@RequestMapping("/admin/edu/course")
public class CourseController {

    @Resource
    private OssFeignService ossFeignService;

    @Resource
    private CourseService courseService;

    @GetMapping("/pageList/{pageNum}/{pageSize}")
    @ApiOperation("条件查询")
    public ResponseResult pageList(@ApiParam(value = "当前页码",required = true) @PathVariable Long pageNum,
                                   @ApiParam(value = "每页记录数", required = true) @PathVariable Long pageSize,
                                   CourseSearch courseSearch) {

        Page<CourseListVo> page = courseService.pageList(pageNum,pageSize,courseSearch);
        List<CourseListVo> list = page.getRecords();
        long total = page.getTotal();

        return ResponseResult.ok().data("elements",list).data("total", total);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据Id查询课程表单信息")
    public ResponseResult getCourseFromById(@ApiParam("课程id") @PathVariable String id) {
        CourseFrom courseFrom = courseService.getCourseFromById(id);

        return ResponseResult.ok().data("element",courseFrom);
    }


    @PostMapping("/add")
    @ApiOperation("添加课程")
    public ResponseResult add(@ApiParam(value = "课程信息", required = true)
                                  @RequestBody CourseFrom courseFrom) {
        String id = courseService.add(courseFrom);
        return ResponseResult.ok().data("courseId",id);
    }

    @DeleteMapping("/remove/{id}")
    @ApiOperation("根据id删除课程")
    public ResponseResult removeById(@ApiParam(value = "讲师id", required = true) @PathVariable String id) {
        //远程调用oss删除阿里云课程封面
        Course course = courseService.getById(id);
        String cover = course.getCover();
        ossFeignService.removePhoto(cover);

        //删除课程相关信息
        courseService.removeCourseById(id);
        return ResponseResult.ok();
    }

    @PutMapping("/update")
    @ApiOperation("更新课程")
    public ResponseResult update(@ApiParam("讲师信息") @RequestBody CourseFrom courseFrom) {
        courseService.updateCourse(courseFrom);
        return ResponseResult.ok();
    }
}

