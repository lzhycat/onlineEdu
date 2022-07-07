package cn.hycat.service.statistics.service.edu.domain.search;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Data
@ApiModel(value = "课程多条件查询vo")
public class CourseSearch {
    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "一级分类id")
    private String subjectParentId;

    @ApiModelProperty(value = "二级分类id")
    private String subjectId;

    @ApiModelProperty(value = "课程标题")
    private String title;
}
