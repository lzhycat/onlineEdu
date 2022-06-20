package cn.hycat.service.oss.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun")
public class AliyunProperties {
      private String endpoint;
      private String bucketName;
      private String accessKeyId;
      private String accessKeySecret;
}
