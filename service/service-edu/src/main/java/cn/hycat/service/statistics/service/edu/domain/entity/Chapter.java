package cn.hycat.service.statistics.service.edu.domain.entity;

import cn.hycat.service.statistics.service.base.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author hy
 * @since 2022-03-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("edu_chapter")
@ApiModel(value="Chapter对象", description="课程")
public class Chapter extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;


}
