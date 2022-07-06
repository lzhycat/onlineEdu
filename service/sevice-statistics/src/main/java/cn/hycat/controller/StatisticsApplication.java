package cn.hycat.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : lzhycat
 * @date : 2022-07-06 17:01
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"cn.hycat"})
@EnableFeignClients
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class);
    }
}
