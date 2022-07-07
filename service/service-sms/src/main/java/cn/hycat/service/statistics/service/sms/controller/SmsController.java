package cn.hycat.service.statistics.service.sms.controller;

import cn.hycat.service.statistics.service.util.entity.ResponseResult;
import cn.hycat.service.statistics.service.util.enums.ResultCodeEnum;
import cn.hycat.service.statistics.service.sms.service.SmsService;
import cn.hycat.service.statistics.service.base.exception.SysException;
import cn.hycat.service.statistics.service.util.util.FormUtils;
import cn.hycat.service.statistics.service.util.util.RandomUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Api(tags = "短信发送")
@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Resource
    private SmsService smsService;

    @Resource
    private RedisTemplate redisTemplate;

    @ApiOperation("给用户发送验证码")
    @GetMapping("/sendCode/{phoneNum}")
    public ResponseResult sendCode(@ApiParam(value = "手机号", required = true)
                                   @PathVariable String phoneNum) {

        //校验手机合法性
        if(Objects.isNull(phoneNum) || !FormUtils.isMobile(phoneNum))
            throw new SysException(ResultCodeEnum.LOGIN_PHONE_ERROR);

        //生成验证码
        String checkCode = RandomUtils.getFourBitRandom();

//        //发送验证码
//        smsService.sendCode(phoneNum, checkCode);

        //验证码存储redis
        redisTemplate.opsForValue().set(phoneNum,checkCode,5, TimeUnit.MINUTES);

        return ResponseResult.ok().message("发送验证码成功");


    }


}
