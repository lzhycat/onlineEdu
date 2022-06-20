package cn.hycat.service.edu.service;

import cn.hycat.service.edu.domain.entity.Teacher;
import cn.hycat.service.edu.domain.search.TeacherSearch;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author hy
 * @since 2022-03-08
 */
public interface TeacherService extends IService<Teacher> {

    Page<Teacher> pageList(Integer pageNum, Integer pageSize, TeacherSearch teacherSearchVo);

    void redoAll();

    List<Map<String, Object>> getNameList(String namePre);

    Map<String, Object> getTeacherAndCourses(String id);

    List<Teacher> selectHotTeacher();
}
