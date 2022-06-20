package cn.hycat.service.ucenter.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx.open")
public class WxProperties {
    private String appId;
    private String appSecret;
    private String redirectUrl;
}
