package cn.hycat.service.oss.controller;

import cn.hycat.service.util.entity.ResponseResult;
import cn.hycat.service.util.enums.ResultCodeEnum;
import cn.hycat.service.oss.service.OssFileService;
import cn.hycat.service.base.exception.SysException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Api(tags = "文件上传")
@RestController
@RequestMapping("/admin/oss")
public class OssFileController {

    @Resource
    private OssFileService fileService;

    //前端调用oss接口上传文件到阿里云 返回一个url给前端；前端再去调用edu的接口 传入teacher对象存储
    @ApiOperation("上传头像")
    @PostMapping("/upload/{module}")
    public ResponseResult onLoad(@ApiParam("上传文件")  MultipartFile file,
                                 @ApiParam("文件所属模块")  @PathVariable String module) throws IOException {
        InputStream inputStream = file.getInputStream();
        String originalFilename = file.getOriginalFilename();

        if(originalFilename.contains("av"))
            throw new SysException(ResultCodeEnum.DONOT_YELLOW);

        String url = fileService.onload(inputStream, originalFilename, module);

        return ResponseResult.ok().data("url",url);
    }

    @ApiOperation("删除阿里云存储url")
    @DeleteMapping("/removeUrl")
    public ResponseResult removeUrl(@ApiParam("删除url") @RequestBody String url) {
        fileService.removeUrl(url);
        return ResponseResult.ok();
    }
}
