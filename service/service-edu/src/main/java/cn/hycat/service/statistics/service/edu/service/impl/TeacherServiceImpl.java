package cn.hycat.service.statistics.service.edu.service.impl;

import cn.hycat.service.statistics.service.edu.domain.entity.Course;
import cn.hycat.service.statistics.service.edu.domain.entity.Teacher;
import cn.hycat.service.statistics.service.edu.mapper.CourseMapper;
import cn.hycat.service.statistics.service.edu.mapper.TeacherMapper;
import cn.hycat.service.statistics.service.edu.service.TeacherService;
import cn.hycat.service.statistics.service.edu.domain.search.TeacherSearch;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author hy
 * @since 2022-03-08
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Resource
    private CourseMapper courseMapper;

    @Override
    public Page<Teacher> pageList(Integer pageNum, Integer pageSize, TeacherSearch teacherSearchVo) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper();
        Page<Teacher> page = new Page(pageNum, pageSize);


        String name = teacherSearchVo.getName();
        Integer level = teacherSearchVo.getLevel();
        String joinDateStart = teacherSearchVo.getJoinDateStart();
        String joinDateEnd = teacherSearchVo.getJoinDateEnd();

        //排序
        wrapper.orderByAsc("sort");

        //查询名字
        if(Objects.nonNull(name)) {
            wrapper.likeRight("name", name);
        }

        //查询头衔
        if(Objects.nonNull(level)) {
            wrapper.eq("level", level);
        }

        //查询开始时间
        if(Objects.nonNull(joinDateStart)) {
            wrapper.ge("join_date", joinDateStart);
        }

        //查询开始时间
        if(Objects.nonNull(joinDateEnd)) {
            wrapper.le("join_date", joinDateEnd);
        }


        return page(page, wrapper);
    }

    @Override
    public void redoAll() {
        getBaseMapper().updateAllIsDel();
    }

    //TODO lambda使用select()报错
    @Override
    public List<Map<String, Object>> getNameList(String namePre) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper();

        wrapper.select("name");
        wrapper.likeRight("name",namePre);
        wrapper.orderByDesc("sort");

        List<Map<String, Object>> maps = getBaseMapper().selectMaps(wrapper);

        return maps;
    }

    @Override
    public Map<String, Object> getTeacherAndCourses(String id) {
        Teacher teacher = getBaseMapper().selectById(id);

        QueryWrapper<Course> wrapper = new QueryWrapper();
        wrapper.eq("teacher_id", id);
        List<Course> courseList = courseMapper.selectList(wrapper);

        HashMap<String, Object> map = new HashMap();
        map.put("teacher",teacher);
        map.put("courseList",courseList);
        return map;
    }

    @Cacheable(value = "index", key = "'selectHotTeacher'")
    @Override
    public List<Teacher> selectHotTeacher() {

        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        queryWrapper.last("limit 4");
        return baseMapper.selectList(queryWrapper);
    }
}
