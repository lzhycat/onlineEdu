package cn.hycat.service.statistics.service.edu.domain.entity;

import java.math.BigDecimal;
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
@TableName("edu_course")
@ApiModel(value="Course对象", description="课程")
public class Course extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;

    @ApiModelProperty(value = "一级分类ID")
    private String subjectParentId;

    @ApiModelProperty(value = "二级分类ID")
    private String subjectId;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "销售数量")
    private Long buyCount;

    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;

    @ApiModelProperty(value = "乐观锁")
    private Long version;

    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;


    public Course() {
    }

    public Course(String id, Long viewCount) {
        this.setId(id);
        this.viewCount = viewCount;
    }
}
