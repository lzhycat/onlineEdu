package cn.hycat.service.cms.service.impl;

import cn.hycat.service.cms.domain.Ad;
import cn.hycat.service.cms.domain.vo.AdVo;
import cn.hycat.service.cms.feign.OssFileService;
import cn.hycat.service.cms.mapper.AdMapper;
import cn.hycat.service.cms.service.AdService;
import cn.hycat.service.util.entity.ResponseResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 广告推荐 服务实现类
 * </p>
 *
 * @author hy
 * @since 2022-04-20
 */
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements AdService {
    @Resource
    private OssFileService ossService;

    @Override
    public Page<AdVo> selectPage(Long page, Long limit) {

        QueryWrapper<AdVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("a.type_id", "a.sort");

        Page<AdVo> pageParam = new Page<>(page, limit);

        List<AdVo> records = baseMapper.selectPageByQueryWrapper(pageParam, queryWrapper);
        pageParam.setRecords(records);
        return pageParam;
    }

    @Override
    public boolean removeAdImageById(String id) {
        Ad ad = baseMapper.selectById(id);
        if(ad != null) {
            String imagesUrl = ad.getImageUrl();
            if(Objects.nonNull(imagesUrl)){
                //删除图片
                ResponseResult r = ossService.removeFile(imagesUrl);
                return r.getSuccess();
            }
        }
        return false;
    }

    @Cacheable(value = "index", key = "'listByAdType'")
    @Override
    public List<Ad> listByAdType(String typeId) {
        QueryWrapper<Ad> wrapper = new QueryWrapper();
        wrapper.eq("type_id", typeId);
        wrapper.orderByDesc("type_id", "sort");
        List<Ad> lists = baseMapper.selectList(wrapper);

        return lists;
    }
}
