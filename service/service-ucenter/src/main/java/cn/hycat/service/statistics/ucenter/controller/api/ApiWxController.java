package cn.hycat.service.statistics.ucenter.controller.api;

import cn.hycat.service.statistics.ucenter.domain.entity.Member;
import cn.hycat.service.statistics.service.util.enums.ResultCodeEnum;
import cn.hycat.service.statistics.ucenter.service.ApiWxService;
import cn.hycat.service.statistics.ucenter.service.MemberService;
import cn.hycat.service.statistics.service.base.exception.SysException;
import cn.hycat.service.statistics.service.util.util.JwtInfo;
import cn.hycat.service.statistics.service.util.util.JwtUtils;
import cn.hycat.service.statistics.ucenter.util.WxProperties;
import cn.hycat.service.statistics.service.util.util.HttpClientUtils;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author 吕泽浩业
 * @version 1.0
 */

@Api(tags = "微信登录")
@Controller
@RequestMapping("/api/ucenter/wx")
@Slf4j
public class ApiWxController {

    @Resource
    private WxProperties wxProperties;

    @Resource
    private MemberService memberService;

    @Resource
    private ApiWxService apiWxService;


    @GetMapping("login")
    @ApiOperation(value = "微信扫码获得Code")
    public String redirectUrl() {
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
         "?appid=%s" +
         "&redirect_uri=%s" +
         "&response_type=code" +
         "&scope=snsapi_login" +
         "&state=%s" +
         "#wechat_re";

        //redirect_uri
        String redirectUri = "";
        try {
             redirectUri = URLEncoder.encode(wxProperties.getRedirectUrl(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //state
        String state = UUID.randomUUID().toString();

        String redirectUrl = String.format(
                baseUrl,
                wxProperties.getAppId(),
                redirectUri,
                state
        );

        return "redirect:" + redirectUrl;
    }

    @GetMapping("callback")
    public String callback(String code, String state){

        //回调被拉起，并获得code和state参数
        log.info("callback被调用");
        log.info("code = " + code);
        log.info("state = " + state);

        if(Objects.isNull(code) || Objects.isNull(state) ){
            log.error("非法回调请求");
            throw new SysException(ResultCodeEnum.ILLEGAL_CALLBACK_REQUEST_ERROR);
        }


        //携带授权临时票据code，和appid以及appsecret请求access_token
        String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
        Map<String, String> accessTokenParam = new HashMap();
        accessTokenParam.put("appid", wxProperties.getAppId());
        accessTokenParam.put("secret", wxProperties.getAppSecret());
        accessTokenParam.put("code", code);
        accessTokenParam.put("grant_type", "authorization_code");
        HttpClientUtils client = new HttpClientUtils(accessTokenUrl, accessTokenParam);

        String result = "";
        try {
            //发送请求
            client.get();
            result = client.getContent();
            System.out.println("result = " + result);
        } catch (Exception e) {
            log.error("获取access_token失败");
            throw new SysException(ResultCodeEnum.FETCH_ACCESSTOKEN_FAILD);
        }

        Gson gson = new Gson();
        HashMap<String, Object> resultMap = gson.fromJson(result, HashMap.class);

        //判断微信获取access_token失败的响应
        Object errcodeObj = resultMap.get("errcode");
        if(errcodeObj != null){
            String errmsg = (String)resultMap.get("errmsg");
            Double errcode = (Double)errcodeObj;
            log.error("获取access_token失败 - " + "message: " + errmsg + ", errcode: " + errcode);
            throw new SysException(ResultCodeEnum.FETCH_ACCESSTOKEN_FAILD);
        }

        //微信获取access_token响应成功
        String accessToken = (String)resultMap.get("access_token");
        String openid = (String)resultMap.get("openid");

        log.info("accessToken = " + accessToken);
        log.info("openid = " + openid);

        Member member = memberService.getByOpenId(openid);

        //用户未注册 -> 注册
        if(Objects.isNull(member)) {
            member = apiWxService.wxRegister(accessToken, openid);
            memberService.save(member);
        }


        // 登录：通过jwt生成token
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setNickname(member.getNickname());
        jwtInfo.setId(member.getId());
        jwtInfo.setAvatar(member.getAvatar());
        String token = JwtUtils.getJwtToken(jwtInfo, 3600);
        return "redirect:http://localhost:3000?token=" + token;
    }
}
