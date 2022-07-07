package cn.hycat.service.statistics.service.trade.client.fallback;

import cn.hycat.service.statistics.service.base.dto.CourseDto;
import cn.hycat.service.statistics.service.trade.client.EduClient;
import org.springframework.stereotype.Service;

/**
 * 熔断保护
 * @author 吕泽浩业
 * @version 1.0
 */
@Service
public class EduCourseServiceFallBack implements EduClient {
    @Override
    public CourseDto getCourseDtoByCourseId(String courseId) {
        return null;
    }
}
