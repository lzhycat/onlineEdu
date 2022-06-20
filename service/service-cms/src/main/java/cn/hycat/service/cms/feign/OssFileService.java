package cn.hycat.service.cms.feign;

import cn.hycat.service.util.entity.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Service
@FeignClient(value = "service-oss")
public interface OssFileService {
    @DeleteMapping("/admin/oss/remove")
    ResponseResult removeFile(@RequestBody String url);
}
