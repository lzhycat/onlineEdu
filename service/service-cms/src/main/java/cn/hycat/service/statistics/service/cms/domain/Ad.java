package cn.hycat.service.statistics.service.cms.domain;

import cn.hycat.service.statistics.service.base.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 广告推荐
 * </p>
 *
 * @author hy
 * @since 2022-04-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("cms_ad")
@ApiModel(value="Ad对象", description="广告推荐")
public class Ad extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "类型ID")
    private String typeId;

    @ApiModelProperty(value = "图片地址")
    private String imageUrl;

    @ApiModelProperty(value = "背景颜色")
    private String color;

    @ApiModelProperty(value = "链接地址")
    private String linkUrl;

    @ApiModelProperty(value = "排序")
    private Integer sort;


}
