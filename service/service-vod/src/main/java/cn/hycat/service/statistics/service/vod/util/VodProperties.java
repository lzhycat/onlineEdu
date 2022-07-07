package cn.hycat.service.statistics.service.vod.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix="aliyun.vod")
public class VodProperties {
    private String keyid;
    private String keysecret;
    private String templateGroupId;
    private String workflowId;
}