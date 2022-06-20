package cn.hycat.service.edu.service;

import cn.hycat.service.edu.domain.entity.Course;
import cn.hycat.service.edu.domain.search.WebCourseSearch;
import cn.hycat.service.edu.domain.vo.CourseFrom;
import cn.hycat.service.base.dto.CourseDto;
import cn.hycat.service.edu.domain.vo.CourseListVo;
import cn.hycat.service.edu.domain.search.CourseSearch;
import cn.hycat.service.edu.domain.vo.WebCourseVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author hy
 * @since 2022-03-08
 */
public interface CourseService extends IService<Course> {

    Page<CourseListVo> pageList(Long pageNum, Long pageSize, CourseSearch courseSearch);

    String add(CourseFrom courseFrom);

    CourseFrom getCourseFromById(String id);

    void updateCourse(CourseFrom courseFrom);

    boolean removeCourseById(String id);

    List<Course> webSelectList(WebCourseSearch webCourseQueryVo);

    WebCourseVo selectWebCourseVoById(String courseId);

    CourseDto getCourseDtoByCourseId(String courseId);

    List<Course> selectHotCourse();
}
