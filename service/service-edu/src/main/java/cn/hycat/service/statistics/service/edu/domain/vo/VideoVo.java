package cn.hycat.service.statistics.service.edu.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Data
@ApiModel(value = "后台课时展示")
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean free;
    private Integer sort;

    private String videoSourceId;
}