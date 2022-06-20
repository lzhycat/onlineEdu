package cn.hycat.service.edu.feign;

import cn.hycat.service.util.entity.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Service
@FeignClient("service-oss")
public interface OssFeignService {

    @DeleteMapping("/admin/oss/removeUrl")
    ResponseResult removePhoto(String url);
}
