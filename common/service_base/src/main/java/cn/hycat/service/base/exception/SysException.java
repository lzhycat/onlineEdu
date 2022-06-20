package cn.hycat.service.base.exception;

import cn.hycat.service.util.enums.ResultCodeEnum;
import lombok.Data;

/**
 * 自定义异常
 */
@Data
public class SysException extends RuntimeException{

    private String msg;

    private Integer code;

    public SysException(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public SysException(ResultCodeEnum resultCodeEnum) {
        this.msg = resultCodeEnum.getMessage();
        this.code = resultCodeEnum.getCode();
    }
}
