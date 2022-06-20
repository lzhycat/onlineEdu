package cn.hycat.service.vod.controller.api;

import cn.hycat.service.util.entity.ResponseResult;
import cn.hycat.service.util.enums.ResultCodeEnum;
import cn.hycat.service.vod.service.VodService;
import cn.hycat.service.base.exception.SysException;
import com.aliyuncs.exceptions.ClientException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Api(tags="阿里云视频点播")
@RestController
@RequestMapping("/api/vod")
@Slf4j
public class ApiVodController {
    @Resource
    private VodService vodService;

    @GetMapping("/getPlayAuth/{videoSourceId}")
    @ApiOperation("获取播放凭证")
    public ResponseResult getPlayAuth(
            @ApiParam(value = "视频id", required = true)
            @PathVariable String videoSourceId) {
        String playAuth = null;
        try {
            playAuth = vodService.getPlayAuth(videoSourceId);
            return ResponseResult.ok().data("playAuth",playAuth);
        } catch (ClientException e) {
            throw new SysException(ResultCodeEnum.FETCH_VIDEO_UPLOADAUTH_ERROR);
        }
    }

}
