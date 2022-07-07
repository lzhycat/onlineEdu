package cn.hycat.service.statistics.service.vod.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
public class AliyunVodSDKUtils {

    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}