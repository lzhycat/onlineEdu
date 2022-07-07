package cn.hycat.service.statistics.service.edu.domain.search;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Data
public class WebCourseSearch implements Serializable {

    private static final long serialVersionUID = 1L;
    private String subjectParentId;
    private String subjectId;
    private String buyCountSort;
    private String gmtCreateSort;
    private String priceSort;
}