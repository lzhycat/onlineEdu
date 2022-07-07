package cn.hycat.service.statistics.client;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : lzhycat
 * @date : 2022-07-06 18:17
 */
@FeignClient(name = "service-ucenter")
public interface UcenterClient {

    @GetMapping("/register/{day}")
    Integer registerQuery(@PathVariable String day);
}
