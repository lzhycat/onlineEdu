package cn.hycat.service.trade.feign.fallback;

import cn.hycat.service.base.dto.CourseDto;
import cn.hycat.service.trade.feign.EduCourseService;
import org.springframework.stereotype.Service;

/**
 * 熔断保护
 * @author 吕泽浩业
 * @version 1.0
 */
@Service
public class EduCourseServiceFallBack implements EduCourseService {
    @Override
    public CourseDto getCourseDtoByCourseId(String courseId) {
        return null;
    }
}
