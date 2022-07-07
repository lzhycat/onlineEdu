package cn.hycat.service.statistics.service.impl;

import cn.hycat.service.statistics.client.UcenterClient;
import cn.hycat.service.statistics.dao.DailyMapper;
import cn.hycat.service.statistics.domain.entity.Daily;
import cn.hycat.service.statistics.service.DailyService;
import cn.hycat.service.statistics.service.util.entity.ResponseResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : lzhycat
 * @date : 2022-07-07 14:14
 */
@Slf4j
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

    @Autowired
    private UcenterClient ucenterClient;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult dailyInfo(String day) {
        //TODO 删除当日记录
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("date_calculated", day);
        baseMapper.delete(wrapper);


        //TODO 调用远程 封装DailyInfo
        Daily daily = new Daily();
        Integer registerNum = ucenterClient.registerQuery(day);


        save(daily);

        return ResponseResult.ok().data("dailyInfo", daily);
    }
}
