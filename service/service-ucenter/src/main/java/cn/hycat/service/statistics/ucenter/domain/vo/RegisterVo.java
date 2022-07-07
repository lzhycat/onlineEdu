package cn.hycat.service.statistics.ucenter.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Data
public class RegisterVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nickname;
    private String mobile;
    private String password;
    private String code;
}
