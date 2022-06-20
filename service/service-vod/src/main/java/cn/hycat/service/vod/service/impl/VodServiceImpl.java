package cn.hycat.service.vod.service.impl;

import cn.hycat.service.util.enums.ResultCodeEnum;
import cn.hycat.service.vod.service.VodService;
import cn.hycat.service.base.exception.SysException;
import cn.hycat.service.vod.util.AliyunVodSDKUtils;
import cn.hycat.service.vod.util.VodProperties;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Service
@Slf4j
public class VodServiceImpl implements VodService {

    @Resource
    private VodProperties vodProperties;

    @Override
    public String uploadVideo(InputStream inputStream, String originalFilename) {
        String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

        UploadStreamRequest request = new UploadStreamRequest(
                vodProperties.getKeyid(),
                vodProperties.getKeysecret(),
                title, originalFilename, inputStream);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);

        String videoId = response.getVideoId();

        //没有正确的返回videoid则说明上传失败
        if(Objects.isNull(videoId)){
            log.error("阿里云上传失败：" + response.getCode() + " - " + response.getMessage());
            throw new SysException(ResultCodeEnum.VIDEO_UPLOAD_ALIYUN_ERROR);
        }

        return videoId;
    }

    @Override
    public String getPlayAuth(String videoSourceId) throws ClientException {

        //初始化client对象
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(
                vodProperties.getKeyid(),
                vodProperties.getKeysecret());

        //创建请求对象
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest ();
        request.setVideoId(videoSourceId);

        //获取响应
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);

        return response.getPlayAuth();
    }
}
