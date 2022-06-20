package cn.hycat.service.base.handler;

import cn.hycat.service.base.exception.SysException;
import cn.hycat.service.util.entity.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult error(Exception e) {
        log.error(String.valueOf(e));
        return ResponseResult.error();
    }


    @ExceptionHandler(SysException.class)
    @ResponseBody
    public ResponseResult error(SysException e) {
        log.error(String.valueOf(e));
        return ResponseResult.error().message(e.getMessage()).code(e.getCode());
    }
}
