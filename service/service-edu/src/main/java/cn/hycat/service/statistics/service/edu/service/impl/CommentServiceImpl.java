package cn.hycat.service.statistics.service.edu.service.impl;

import cn.hycat.service.statistics.service.edu.domain.entity.Comment;
import cn.hycat.service.statistics.service.edu.mapper.CommentMapper;
import cn.hycat.service.statistics.service.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author hy
 * @since 2022-03-08
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
