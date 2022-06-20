package cn.hycat.service.base.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Data
public class CourseDto implements Serializable {
    private static final long serialVersionUID=1L;
    private String courseId;
    private String courseTitle;
    private String courseCover;
    private String teacherName;
    private BigDecimal price;
}
