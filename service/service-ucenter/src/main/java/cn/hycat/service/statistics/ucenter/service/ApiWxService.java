package cn.hycat.service.statistics.ucenter.service;

import cn.hycat.service.statistics.ucenter.domain.entity.Member;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
public interface ApiWxService {
    //利用微信注册
    Member wxRegister(String accessToken, String openId);
}
