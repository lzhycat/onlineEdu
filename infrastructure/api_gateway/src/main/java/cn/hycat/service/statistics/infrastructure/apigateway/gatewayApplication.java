package cn.hycat.service.statistics.infrastructure.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class gatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(gatewayApplication.class, args);
    }
}
