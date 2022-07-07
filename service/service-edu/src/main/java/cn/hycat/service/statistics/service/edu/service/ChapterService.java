package cn.hycat.service.statistics.service.edu.service;

import cn.hycat.service.statistics.service.edu.domain.entity.Chapter;
import cn.hycat.service.statistics.service.edu.domain.vo.ChapterVo;
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
public interface ChapterService extends IService<Chapter> {

    boolean removeChapterById(String id);

    List<ChapterVo> nestedList(String courseId);
}
