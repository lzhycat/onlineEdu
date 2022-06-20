package cn.hycat.service.base.handler;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Redis获取数据 防止数据转换错误
 */
@Component
public class RedisHandler {
    @Resource
    private RedisTemplate redisTemplate;

    //避免Integer转Long(报错)
    public Long getViewCountFormRedis(String id) {
        Object ViewCountObject =  redisTemplate.opsForValue().get("View_"+id);
        Long viewCount = (ViewCountObject instanceof Integer) ?
                Long.parseLong(ViewCountObject.toString()) : (Long)ViewCountObject;

        return viewCount;
    }
}
