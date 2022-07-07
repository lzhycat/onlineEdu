package cn.hycat.service.statistics.service.edu.client;

import cn.hycat.service.statistics.service.util.entity.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Service
@FeignClient(name = "service-oss")
public interface OssClient {

    @DeleteMapping("/admin/oss/removeUrl")
    ResponseResult removePhoto(String url);
}
