package cn.hycat.service.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author : lzhycat
 * @date : 2022-07-06 17:01
 */
@EnableScheduling
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"cn.hycat"})
@EnableFeignClients
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class);
    }
}
