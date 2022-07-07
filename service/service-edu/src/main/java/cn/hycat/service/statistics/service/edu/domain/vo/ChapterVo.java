package cn.hycat.service.statistics.service.edu.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Data
@ApiModel(value = "后台章节展示")
public class ChapterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Integer sort;
    private List<VideoVo> children = new ArrayList<>();
}