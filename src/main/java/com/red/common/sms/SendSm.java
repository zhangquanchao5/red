package com.red.common.sms;

import com.alibaba.fastjson.JSON;
import com.red.common.util.PropertiesUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;

/**
 * Created by huichao on 2015/7/16.
 */
public class SendSm {
    private static String Url = "http://121.43.107.8:8888/sms.aspx";
    public static final String SUCCE_CODE="2";

    public synchronized static SmsResponse sendSms(String mobile,String content){
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);
        client.getParams().setContentCharset("utf-8");
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
        SmsResponse smsResponse=new SmsResponse();

        NameValuePair[] data = {//提交短信
                new NameValuePair("action", "send"),
                new NameValuePair("userid", PropertiesUtil.getString("SEND.ENTERPRISEID")),
                new NameValuePair("account", PropertiesUtil.getString("SEND.USERNAME")),
                new NameValuePair("password",PropertiesUtil.getString("SEND.PWD")), //密码可以使用明文密码或使用32位MD5加密
                new NameValuePair("mobile", mobile),
                new NameValuePair("content", content),
                new NameValuePair("sendTime", ""),
        };

        method.setRequestBody(data);


        try {
            client.executeMethod(method);

            String SubmitResult =method.getResponseBodyAsString();


            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();
            smsResponse.setCode(root.elementText("returnstatus"));
            smsResponse.setMsg(root.elementText("message"));
            smsResponse.setTaskID(root.elementText("taskID"));
            smsResponse.setRemainpoint(root.elementText("remainpoint"));
            smsResponse.setSuccessCounts(root.elementText("successCounts"));
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return smsResponse;
    }

    public static void main(String []args){
        System.out.println(JSON.toJSONString(sendSms("15201175465","恭喜获得一个分享现金红包，已放入您的账户，赶快下载APP拆包，参与活动还有更多红包福利t.cn/R4fiyEr；回复TD退订【有你学】")));
    }




}
