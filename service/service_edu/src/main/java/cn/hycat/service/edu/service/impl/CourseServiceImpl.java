package cn.hycat.service.edu.service.impl;

import cn.hycat.service.edu.constants.CourseConstant;
import cn.hycat.service.edu.domain.entity.*;
import cn.hycat.service.edu.domain.search.WebCourseSearch;
import cn.hycat.service.edu.domain.vo.CourseFrom;
import cn.hycat.service.edu.domain.vo.WebCourseVo;
import cn.hycat.service.edu.mapper.*;
import cn.hycat.service.edu.service.CourseDescriptionService;
import cn.hycat.service.edu.service.CourseService;
import cn.hycat.service.base.dto.CourseDto;
import cn.hycat.service.base.handler.RedisHandler;
import cn.hycat.service.edu.domain.vo.CourseListVo;
import cn.hycat.service.edu.domain.search.CourseSearch;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author hy
 * @since 2022-03-08
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private VideoMapper videoMapper;

    @Resource
    private ChapterMapper chapterMapper;

    @Resource
    private CourseCollectMapper courseCollectMapper;

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private CourseDescriptionService courseDescriptionService;

    @Resource
    private RedisHandler redisHandler;

    @Override
    public Page<CourseListVo> pageList(Long pageNum, Long pageSize, CourseSearch courseSearch) {

        //CourseSearch
        String subjectParentId = courseSearch.getSubjectParentId();
        String subjectId = courseSearch.getSubjectId();
        String teacherId = courseSearch.getTeacherId();
        String title = courseSearch.getTitle();

        QueryWrapper<CourseListVo> wrapper = new QueryWrapper();
        if(Objects.nonNull(subjectParentId))
            wrapper.eq("c.subject_parent_id", subjectParentId);
        if(Objects.nonNull(subjectId))
            wrapper.eq("c.subject_id", subjectId);
        if(Objects.nonNull(teacherId))
            wrapper.eq("c.teacher_id", teacherId);
        if(Objects.nonNull(title))
            wrapper.likeLeft("c.title", title);

        //wrapper当做参数传入mapper层
        Page<CourseListVo> pageParam = new Page(pageNum, pageSize);
        List<CourseListVo> courseListVos = baseMapper.pageList(pageParam, wrapper);

        return pageParam.setRecords(courseListVos);
    }

    @Override
    public String add(CourseFrom courseFrom) {
        Course course = new Course();
        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseFrom,course);
        course.setStatus(CourseConstant.COURSE_DRAFT);
        save(course);

        courseDescription.setDescription(courseFrom.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionService.save(courseDescription);

        return course.getId();
    }

    @Override
    public CourseFrom getCourseFromById(String id) {
        CourseFrom courseFrom = new CourseFrom();

        //拷贝课程基本信息
        Course course = getById(id);
        BeanUtils.copyProperties(course,courseFrom);

        //拷贝课程描述信息
        CourseDescription description = courseDescriptionService.getById(id);
        courseFrom.setDescription(description.getDescription());

        return courseFrom;
    }

    @Override
    public void updateCourse(CourseFrom courseFrom) {
        Course course = new Course();
        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseFrom,course);
        updateById(course);

        courseDescription.setDescription(courseFrom.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionService.updateById(courseDescription);
    }

    //TODO ： eq的Lambda无法使用
    @Override
    public boolean removeCourseById(String id) {
        //删除video视频
        QueryWrapper<Video> videoWrapper = new QueryWrapper();
        videoWrapper.eq("course_id",id);
        videoMapper.delete(videoWrapper);

        //删除chapter大纲
        QueryWrapper<Chapter> chapterWrapper = new QueryWrapper();
        chapterWrapper.eq("course_id",id);
        chapterMapper.delete(chapterWrapper);

        //删除comment评论
        QueryWrapper<Comment> commentWrapper = new QueryWrapper();
        commentWrapper.eq("course_id",id);
        commentMapper.delete(commentWrapper);

        //删除courseCollect收藏
        QueryWrapper<CourseCollect> courseCollectWrapper = new QueryWrapper();
        courseCollectWrapper.eq("course_id",id);
        courseCollectMapper.delete(courseCollectWrapper);

        //删除courseDescription课程描述
        courseDescriptionService.removeById(id);

        //删除course课程
        return removeById(id);
    }

    @Override
    public List<Course> webSelectList(WebCourseSearch webCourseQueryVo) {

        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();

        //查询已发布的课程
        queryWrapper.eq("status", CourseConstant.COURSE_DRAFT);

        if (Objects.nonNull(webCourseQueryVo.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id", webCourseQueryVo.getSubjectParentId());
        }

        if (Objects.nonNull(webCourseQueryVo.getSubjectId())) {
            queryWrapper.eq("subject_id", webCourseQueryVo.getSubjectId());
        }

        if (Objects.nonNull(webCourseQueryVo.getBuyCountSort())) {
            queryWrapper.orderByDesc("buy_count");
        }

        if (Objects.nonNull(webCourseQueryVo.getGmtCreateSort())) {
            queryWrapper.orderByDesc("gmt_create");
        }

        if (Objects.nonNull(webCourseQueryVo.getPriceSort())) {
            queryWrapper.orderByDesc("price");
        }

        List<Course> courseList = baseMapper.selectList(queryWrapper);
        for(int i = 0; i < courseList.size(); i++) {
            String id = courseList.get(i).getId();
            //从Redis拿取ViewCount
            Long viewCount = (Long) redisTemplate.opsForHash().get("course:viewCount", id);
            //ViewCount更新
            courseList.get(i).setViewCount(viewCount);
        }

        return courseList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public WebCourseVo selectWebCourseVoById(String id) {

        //Redis浏览量自增
        redisTemplate.opsForHash().increment("course:viewCount", id, 1);

        //拿Redis中ViewCount
        Long viewCount = (Long) redisTemplate.opsForHash().get("course:viewCount", id);

        //Redis获取浏览量注入返回值
        WebCourseVo webCourseVo = baseMapper.selectWebCourseVoById(id);
        webCourseVo.setViewCount(viewCount);

        return webCourseVo;
    }

    @Override
    public CourseDto getCourseDtoByCourseId(String courseId) {
        return getBaseMapper().getCourseDtoByCourseId(courseId);
    }

    @Cacheable(value = "index", key = "'selectHotCourse'")
    @Override
    public List<Course> selectHotCourse() {

        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("view_count");
        queryWrapper.last("limit 8");
        return baseMapper.selectList(queryWrapper);
    }
}
