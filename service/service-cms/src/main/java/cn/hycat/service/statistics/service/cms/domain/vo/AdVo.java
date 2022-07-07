package cn.hycat.service.statistics.service.cms.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Data
public class AdVo implements Serializable {

    private static final long serialVersionUID=1L;
    private String id;
    private String title;
    private Integer sort;
    private String type;
}
