package cn.hycat.service.statistics.service.edu.domain.vo;

import cn.hycat.service.statistics.service.edu.domain.entity.Subject;
import cn.hycat.service.statistics.service.base.model.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Data
@ApiModel("后台课程分类父子关系模型")
public class SubjectTree extends BaseEntity {

    @ApiModelProperty(value = "类别名称")
    private String title;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;

    @ApiModelProperty(value = "子分类")
    private List<Subject> childSubjects;

    @ApiModelProperty(value = "父ID")
    private String parentId;
}
