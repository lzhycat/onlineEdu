package cn.hycat.service.statistics.service.edu.domain.entity;

import cn.hycat.service.statistics.service.base.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 课程科目
 * </p>
 *
 * @author hy
 * @since 2022-03-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("edu_subject")
@ApiModel(value="Subject对象", description="课程科目")
public class Subject extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "类别名称")
    private String title;

    @ApiModelProperty(value = "父ID")
    private String parentId;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;


}
