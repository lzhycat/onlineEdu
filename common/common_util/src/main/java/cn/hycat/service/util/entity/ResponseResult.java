package cn.hycat.service.util.entity;
import cn.hycat.service.util.enums.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Data
@ApiModel(value = "全局统一返回结果")
public class ResponseResult {

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    public ResponseResult(){}

    public static ResponseResult ok(){
        ResponseResult r = new ResponseResult();
        r.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return r;
    }

    public static ResponseResult error(){
        ResponseResult r = new ResponseResult();
        r.setSuccess(ResultCodeEnum.UNKNOWN_REASON.getSuccess());
        r.setCode(ResultCodeEnum.UNKNOWN_REASON.getCode());
        r.setMessage(ResultCodeEnum.UNKNOWN_REASON.getMessage());
        return r;
    }

    public static ResponseResult setResult(ResultCodeEnum resultCodeEnum){
        ResponseResult r = new ResponseResult();
        r.setSuccess(resultCodeEnum.getSuccess());
        r.setCode(resultCodeEnum.getCode());
        r.setMessage(resultCodeEnum.getMessage());
        return r;
    }

    public ResponseResult success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public ResponseResult message(String message){
        this.setMessage(message);
        return this;
    }

    public ResponseResult code(Integer code){
        this.setCode(code);
        return this;
    }

    public ResponseResult data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public ResponseResult data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}