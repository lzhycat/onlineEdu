package cn.hycat.service.statistics.ucenter.domain.entity;

import cn.hycat.service.statistics.service.base.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 会员表
 * @TableName ucenter_member
 */
@TableName(value ="ucenter_member")
@Data
@ApiModel("用户会员表")
public class Member extends BaseEntity implements Serializable {

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别 1 男，2 女
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户签名
     */
    private String sign;

    /**
     * 是否禁用 1（true）已禁用，  0（false）未禁用
     */
    private Integer isDisabled;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    private Integer isDeleted;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}