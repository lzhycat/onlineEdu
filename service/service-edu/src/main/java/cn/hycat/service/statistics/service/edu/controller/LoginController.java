package cn.hycat.service.statistics.service.edu.controller;

import cn.hycat.service.statistics.service.util.entity.ResponseResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @PostMapping("/login")
    public ResponseResult login() {
        return ResponseResult.ok().data("token", "token");
    }

    @GetMapping("/info")
    public ResponseResult info() {
        return ResponseResult.ok()
                .data("token", "token")
                .data("roles","[admin]")
                .data("name","name")
                .data("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
    }

    @PostMapping("/logout")
    public ResponseResult logout() {
        return ResponseResult.ok();
    }
}
