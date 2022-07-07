package cn.hycat.service.statistics.service.edu.mapper;

import cn.hycat.service.statistics.service.edu.domain.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author hy
 * @since 2022-03-08
 */
public interface TeacherMapper extends BaseMapper<Teacher> {

    void updateAllIsDel();
}
