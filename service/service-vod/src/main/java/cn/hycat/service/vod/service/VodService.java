package cn.hycat.service.vod.service;

import com.aliyuncs.exceptions.ClientException;

import java.io.InputStream;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
public interface VodService {
    String uploadVideo(InputStream inputStream, String originalFilename);

    String getPlayAuth(String videoSourceId) throws ClientException;
}
