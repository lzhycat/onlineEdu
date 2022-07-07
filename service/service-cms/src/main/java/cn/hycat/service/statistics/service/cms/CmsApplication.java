package cn.hycat.service.statistics.service.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 吕泽浩业
 * @version 1.0
 */

@SpringBootApplication
@ComponentScan({"cn.hycat"})
@EnableDiscoveryClient
@EnableFeignClients
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
