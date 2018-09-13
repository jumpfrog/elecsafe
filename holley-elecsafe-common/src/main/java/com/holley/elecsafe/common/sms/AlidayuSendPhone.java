package com.holley.elecsafe.common.sms;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.holley.platform.common.util.JsonUtil;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import net.sf.json.JSONObject;

/**
 * 阿里大鱼短信发送
 * 
 * @author sc
 */
public class AlidayuSendPhone {

    private final static Logger        logger           = Logger.getLogger(AlidayuSendPhone.class);
    private static String              APP_KEY          = "23393511";
    private static String              APP_SECRET       = "1957e25bdc0b14faffa71c63cdbcd163";
    private static String              SEND_URL         = "http://gw.api.taobao.com/router/rest";  // 短信接口地址
    private static String              SMS_TYPE         = "normal";                                // 短信类型默认normal（普通）
    private static String              TEMPLATE_URGEFEE = "templateurgefee";                       // 预付费短信催费模版
    private static String              TEMPLATE_INVERTERALARM = "template_inverteralarm";
    private static String              SIGN_TITLE       = "华立能管家";                                 // 验证码签名名称
    private static JSONObject          TEMPLATEJSON     = null;                                    // 短信模版
    private static Map<String, Object> PARAMS           = new HashMap<String, Object>();

    public static void init(String appKey, String appSecret, String sendUrl, String template, String signTitle) {
        APP_KEY = appKey;
        APP_SECRET = appSecret;
        SEND_URL = sendUrl;
        SIGN_TITLE = signTitle;
        TEMPLATEJSON = JSONObject.fromObject(template);
    }

    /**
     * 能源管理云平台：预付费催费短信 <br>
     * 模板类型:短信通知<br>
     * 模板名称:费用提醒<br>
     * 模板ID:SMS_36365137<br>
     * 模板内容:尊敬的用户，截止${datatime}，您本月已使用电费${freezemoney}元，账户实际可用余额${usablemoney}元，预计可用${days}天，请及时充值，以免影响您的正常用电！${edisc}
     * 为您服务。<br>
     */
    public static boolean sendUrgeFeeMsg(String phone, String datatime, String freezemoney, String usablemoney, String days, String edisc) {
        if (TEMPLATEJSON == null) {
            return false;
        }
        PARAMS.clear();
        PARAMS.put("datatime", datatime);
        PARAMS.put("freezemoney", freezemoney);
        PARAMS.put("usablemoney", usablemoney);
        PARAMS.put("days", days);
        PARAMS.put("edisc", edisc);
        String content = JsonUtil.map2json(PARAMS);
        return baseMsg(phone, content, SIGN_TITLE, TEMPLATEJSON.getString(TEMPLATE_URGEFEE));
    }
    /**
     * 光伏电站管理：逆变器报警短信 <br>
     * 模板类型:短信通知<br>
     * 模板名称:逆变器报警   <br>
     * 模板ID:SMS_74625024<br>
     * 模板内容:截止今日20:00，${name}旗下逆变器共有${count}条严重报警！ 系统短信，请勿回复！<br>
     */
    public static boolean sendInverterAlarmMsg(String phone, String name, Integer count) {
        if (TEMPLATEJSON == null) {
            return false;
        }
        PARAMS.clear();
        PARAMS.put("name", name);
        PARAMS.put("count", count);
        String content = JsonUtil.map2json(PARAMS);
        return baseMsg(phone, content, SIGN_TITLE, TEMPLATEJSON.getString(TEMPLATE_INVERTERALARM));
    }

    private static boolean baseMsg(String phone, String content, String signName, String templateCode) {
        if (StringUtils.isEmpty(templateCode)) {
            return false;
        }
        boolean result = false;
        String resultMsg = "";
        TaobaoClient client = new DefaultTaobaoClient(SEND_URL, APP_KEY, APP_SECRET);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("");
        req.setSmsType(SMS_TYPE);
        req.setSmsFreeSignName(signName);//【阿里大于】欢迎使用阿里大于服务。
        req.setSmsParamString(content);//针对模板“验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！”，传参时需传入{"code":"1234","product":"alidayu"}
        req.setRecNum(phone);
        req.setSmsTemplateCode(templateCode);
        AlibabaAliqinFcSmsNumSendResponse rsp;
        try {
            rsp = client.execute(req);
            resultMsg = rsp.getBody();
            logger.info(resultMsg);
            if (JSONObject.fromObject(resultMsg).get("alibaba_aliqin_fc_sms_num_send_response") != null) {
                result = true;
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return result;
    }

}
