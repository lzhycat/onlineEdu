package cn.hycat.service.statistics.service.sms.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "txunyun")
public class SmsProperties {
    private String appID;
    private String appKey;
    private String templateId;
    private String signName;
}

