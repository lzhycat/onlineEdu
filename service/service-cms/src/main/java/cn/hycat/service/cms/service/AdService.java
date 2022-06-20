package cn.hycat.service.cms.service;

import cn.hycat.service.cms.domain.Ad;
import cn.hycat.service.cms.domain.vo.AdVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 广告推荐 服务类
 * </p>
 *
 * @author hy
 * @since 2022-04-20
 */
public interface AdService extends IService<Ad> {
    Page<AdVo> selectPage(Long page, Long limit);

    boolean removeAdImageById(String id);

    List<Ad> listByAdType(String typeId);
}
