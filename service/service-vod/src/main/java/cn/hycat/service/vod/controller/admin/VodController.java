package cn.hycat.service.vod.controller.admin;

import cn.hycat.service.util.entity.ResponseResult;
import cn.hycat.service.util.enums.ResultCodeEnum;
import cn.hycat.service.vod.service.VodService;
import cn.hycat.service.base.exception.SysException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Api(tags="阿里云视频点播")
@RestController
@RequestMapping("/admin/vod")
@Slf4j
public class VodController {
    @Resource
    private VodService vodService;

    @PostMapping("/upload")
    @ApiOperation("上传阿里云视频")
    public ResponseResult uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String videoId = vodService.uploadVideo(inputStream, originalFilename);
            return ResponseResult.ok().message("视频上传成功").data("videoId", videoId);
        } catch (IOException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new SysException(ResultCodeEnum.VIDEO_UPLOAD_TOMCAT_ERROR);
        }
    }


}
