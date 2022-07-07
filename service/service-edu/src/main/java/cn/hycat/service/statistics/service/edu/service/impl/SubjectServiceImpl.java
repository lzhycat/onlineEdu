package cn.hycat.service.statistics.service.edu.service.impl;

import cn.hycat.service.statistics.service.edu.domain.entity.Subject;
import cn.hycat.service.statistics.service.edu.domain.vo.SubjectTree;
import cn.hycat.service.statistics.service.edu.mapper.SubjectMapper;
import cn.hycat.service.statistics.service.edu.service.SubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author hy
 * @since 2022-03-08
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public List<SubjectTree> listTree() {
        List<Subject> list = list();
        List<SubjectTree> subjectTrees = new ArrayList();
        for(Subject subject : list) {
            if(subject.getParentId().equals("0")) {
                //父subject->父subjectTree
                SubjectTree subjectTree = new SubjectTree();
                BeanUtils.copyProperties(subject,subjectTree);

                //查询子分类 注入
                QueryWrapper<Subject> wrapper = new QueryWrapper();
                wrapper.eq("parent_id",subject.getId());
                List<Subject> list1 = getBaseMapper().selectList(wrapper);
                subjectTree.setChildSubjects(list1);

                subjectTrees.add(subjectTree);
            }
        }

        return subjectTrees;
    }
}
