package cn.hycat.service.statistics.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"cn.hycat"})
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
