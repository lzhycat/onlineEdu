package cn.hycat.service.statistics.service.edu.service;

import cn.hycat.service.statistics.service.edu.domain.entity.Subject;
import cn.hycat.service.statistics.service.edu.domain.vo.SubjectTree;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author hy
 * @since 2022-03-08
 */
public interface SubjectService extends IService<Subject> {
    List<SubjectTree> listTree();
}
