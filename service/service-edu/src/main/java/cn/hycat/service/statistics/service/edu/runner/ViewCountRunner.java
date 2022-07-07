package cn.hycat.service.statistics.service.edu.runner;

import cn.hycat.service.statistics.service.edu.domain.entity.Course;
import cn.hycat.service.statistics.service.edu.service.CourseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 启动预处理
 * @author 吕泽浩业
 * @version 1.0
 */
@Component
public class ViewCountRunner implements CommandLineRunner {

    @Resource
    private CourseService courseService;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        //查询ViewCount
        List<Course> courses = courseService.list();

        //存入Redis
        Map<String, Integer> viewCountMap = courses.stream()
                .collect(Collectors.toMap(Course::getId,
                        course -> course.getViewCount().intValue()));//后面需要这个值自增 故设为Integer
        redisTemplate.opsForHash().putAll("course:viewCount", viewCountMap);
    }
}
