package cn.hycat.service.edu.domain.search;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Data
@ApiModel(value = "教师多条件查询vo")
public class TeacherSearch {

    @ApiModelProperty(value = "讲师姓名")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "开始时间", example = "2000-01-01")
    private String joinDateStart;

    @ApiModelProperty(value = "结束时间", example = "2000-01-01")
    private String joinDateEnd;
}
