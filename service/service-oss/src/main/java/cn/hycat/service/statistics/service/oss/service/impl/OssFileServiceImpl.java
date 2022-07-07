package cn.hycat.service.statistics.service.oss.service.impl;

import cn.hycat.service.statistics.service.oss.service.OssFileService;
import cn.hycat.service.statistics.service.oss.util.AliyunProperties;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Service
public class OssFileServiceImpl implements OssFileService {

    @Resource
    private AliyunProperties readConfigFileHandler;

    @Override
    public String  onload(InputStream inputStream, String originalFilename, String module) {
        String endpoint = readConfigFileHandler.getEndpoint();
        String accessKeyId = readConfigFileHandler.getAccessKeyId();
        String accessKeySecret = readConfigFileHandler.getAccessKeySecret();
        String bucketName = readConfigFileHandler.getBucketName();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        //图片在aliyun存储地点+图片名称
        String nowDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        String uuid = UUID.randomUUID().toString();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = module + "/" + nowDate + "/" + uuid + suffix;

        ossClient.putObject(bucketName, objectName, inputStream);

        ossClient.shutdown();

        return "https://" + bucketName + "." +endpoint + "/" +objectName;
    }

    @Override
    public void removeUrl(String url) {
        String endpoint = readConfigFileHandler.getEndpoint();
        String accessKeyId = readConfigFileHandler.getAccessKeyId();
        String accessKeySecret = readConfigFileHandler.getAccessKeySecret();
        String bucketName = readConfigFileHandler.getBucketName();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        String objectName = url.substring(("https://"+bucketName+"."+endpoint+"/").length());
        ossClient.deleteObject(bucketName, objectName);
        ossClient.shutdown();
    }
}
