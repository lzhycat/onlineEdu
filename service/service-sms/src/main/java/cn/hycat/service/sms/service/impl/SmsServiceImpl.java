package cn.hycat.service.sms.service.impl;

import cn.hycat.service.sms.service.SmsService;
import cn.hycat.service.sms.util.SmsProperties;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Resource
    private SmsProperties smsProperties;

    @Override
    public void sendCode(String phoneNum, String checkCode) {
        String appID = smsProperties.getAppID();
        String appKey = smsProperties.getAppKey();
        String templateId = smsProperties.getTemplateId();
        String signName = smsProperties.getSignName();
        String[] params = {checkCode};

        SmsSingleSender sender = new SmsSingleSender(Integer.parseInt(appID), appKey);

        try {
            SmsSingleSenderResult result = sender.sendWithParam("86", phoneNum,
                    Integer.parseInt(templateId), params, signName, "", "");
            System.out.println(result.toString());
        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
