package cn.hycat.service.statistics.service.oss.service;

import java.io.InputStream;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
public interface OssFileService {
    String onload(InputStream inputStream, String originalFilename, String module);

    void removeUrl(String url);
}
