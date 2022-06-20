package cn.hycat.service.ucenter.service.impl;

import cn.hycat.service.ucenter.domain.entity.Member;
import cn.hycat.service.ucenter.service.ApiWxService;
import cn.hycat.service.util.util.HttpClientUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 吕泽浩业
 * @version 1.0
 */
@Service
@Slf4j
public class ApiWxServiceImpl implements ApiWxService {
    @Override
    public Member wxRegister(String accessToken, String openId) {
        //根据access_token获取微信用户的基本信息
        String UnionIdUrl = "https://api.weixin.qq.com/sns/userinfo";
        // "?access_token=ACCESS_TOKEN"
        // "&openid=OPENID"
        Map<String, String> UnionIdUrlParam = new HashMap();
        UnionIdUrlParam.put("access_token", accessToken);
        UnionIdUrlParam.put("openid", openId);
        HttpClientUtils UnionIdUrlClient = new HttpClientUtils(UnionIdUrl, UnionIdUrlParam);

        String result = "";
        try {
            UnionIdUrlClient.get();
            result = UnionIdUrlClient.getContent();
        } catch (Exception e) {
            log.error("微信获取个人信息失败");
        }


        Gson gson = new Gson();
        HashMap<String, Object> resultMap = gson.fromJson(result, HashMap.class);
        String nickname = (String) resultMap.get("nickname");
        Double sex = (Double) resultMap.get("sex");
        String avatar = (String) resultMap.get("headimgurl");

        Member member = new Member();
        member.setOpenid(openId);
        member.setNickname(nickname);
        member.setSex(sex.intValue());
        member.setAvatar(avatar);
        return member;
    }
}
