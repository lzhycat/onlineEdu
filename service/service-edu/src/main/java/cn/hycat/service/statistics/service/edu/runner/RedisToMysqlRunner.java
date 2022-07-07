package cn.hycat.service.statistics.service.edu.runner;

import cn.hycat.service.statistics.service.edu.domain.entity.Course;
import cn.hycat.service.statistics.service.edu.service.CourseService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Redis数据同步到MySQL中
 * @author 吕泽浩业
 * @version 1.0
 */
@Component
public class RedisToMysqlRunner {

    @Resource
    private CourseService courseService;

    @Resource
    private RedisTemplate redisTemplate;


    @Scheduled(cron = "1 * * * * ?")
    public void updateViewCount() {
        //获取缓存中的浏览量
        Map<String, Integer> viewCountMap = redisTemplate.opsForHash().entries("course:viewCount");

        //将map中的每一个key和value都封装为Course对象  最终收集
        List<Course> courses = viewCountMap.entrySet().stream()
                .map(entry -> new Course(entry.getKey(), entry.getValue().longValue()))
                .collect(Collectors.toList());

        //updateBatchById接受的是实体类List集合
        courseService.updateBatchById(courses);
    }
}
