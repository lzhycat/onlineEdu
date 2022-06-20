package cn.hycat.service.sms.service;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
public interface SmsService {
    void sendCode(String phoneNum, String checkCode);
}
