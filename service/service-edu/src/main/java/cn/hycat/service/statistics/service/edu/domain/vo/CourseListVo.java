package cn.hycat.service.statistics.service.edu.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Data
@ApiModel(value = "后台课程展示vo")
public class CourseListVo {

    @ApiModelProperty(value = "课程id")
    private String id;

    @ApiModelProperty(value = "讲师姓名")
    private String teacherName;

    @ApiModelProperty(value = "一级分类名")
    private String subjectParentName;

    @ApiModelProperty(value = "二级分类名")
    private String subjectName;

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

    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
}
