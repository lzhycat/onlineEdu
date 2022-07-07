package cn.hycat.service.statistics.service;

import cn.hycat.service.statistics.service.util.entity.ResponseResult;

public interface DailyService {
    ResponseResult dailyInfo(String day);
}
