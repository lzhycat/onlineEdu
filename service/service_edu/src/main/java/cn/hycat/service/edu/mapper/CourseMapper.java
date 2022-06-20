package cn.hycat.service.edu.mapper;

import cn.hycat.service.edu.domain.entity.Course;
import cn.hycat.service.base.dto.CourseDto;
import cn.hycat.service.edu.domain.vo.CourseListVo;
import cn.hycat.service.edu.domain.vo.WebCourseVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author hy
 * @since 2022-03-08
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<CourseListVo> pageList(Page<CourseListVo> pageParam,
                                @Param(Constants.WRAPPER) QueryWrapper<CourseListVo> queryWrapper);

    WebCourseVo selectWebCourseVoById(String courseId);

    CourseDto getCourseDtoByCourseId(String courseId);
}
