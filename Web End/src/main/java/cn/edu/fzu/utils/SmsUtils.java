package cn.edu.fzu.utils;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import java.util.HashMap;
import java.util.Random;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.*;
import org.junit.jupiter.api.Test;;

public class SmsUtils {
    private static String SecretId="AKIDlhQt7FZj8dvxlJvfRyHjS2NT1qPj3j8I";
    private static String SecretKey="3OH0QZVCfHfdKmyxA6nEOIyO9euwiKvI";
    public static String getSmsCode(String phone,String smsCode){
        try{
            Credential cred = new Credential(SecretId, SecretKey);
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            SmsClient client = new SmsClient(cred, "", clientProfile);
            SendSmsRequest req = new SendSmsRequest();
            String[] phoneNumberSet1 = {"+86"+phone};
            req.setPhoneNumberSet(phoneNumberSet1);
            req.setTemplateID("928310");
            req.setSign("Spring学习2");
            req.setSmsSdkAppid("1400510605");
            String[] templateParamSet1 = {smsCode};
            req.setTemplateParamSet(templateParamSet1);
            SendSmsResponse resp = client.SendSms(req);
            System.out.println(SendSmsResponse.toJsonString(resp));
            return SendSmsResponse.toJsonString(resp);

        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
            return null;
    }


     public static String genSmsCode(){
        Integer r = (int) (Math.random()*9000)+1000;
        return String.valueOf(r);
    }





    public static String sendCode (String to) {
        //生产环境请求地址：app.cloopen.com
        String serverIp = "app.cloopen.com";
        //请求端口
        String serverPort = "8883";
        //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
        String accountSId = "8aaf070878d419aa0178d4b696f8008b";
        String accountToken = "6a1d22487d0d486e824d05c42b9b0708";
        //请使用管理控制台中已创建应用的APPID
        String appId = "8aaf070878d419aa0178d4b698340091";
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        String code = "4893"; //String.valueOf((int)(Math.random() * 9000 + 1000));  //可选 扩展码，四位数字 0~9999
        String[] datas = {code,"2"};
        String templateId = "1";
        //HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas);
        HashMap<String, Object> result = sdk.sendTemplateSMS(to, templateId, datas, null, null);
        if ("000000".equals(result.get("statusCode"))) {
            return code;
        } else {
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
            return null;
        }
    }

}
