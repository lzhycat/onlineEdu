package cn.hycat.service.trade.feign;

import cn.hycat.service.base.dto.CourseDto;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@FeignClient(value = "service-edu")
public interface EduCourseService {
    @GetMapping("/api/edu/course/inner/get-course-dto/{courseId}")
    CourseDto getCourseDtoByCourseId( @ApiParam(value = "课程ID", required = true)
                                      @PathVariable String courseId);
}
