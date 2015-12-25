package com.red.web;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;


import com.red.common.code.SplitCode;
import com.red.common.StringUtil;
import com.red.common.StudyLogger;
import com.red.common.apibean.AuthHeaderBean;
import com.red.common.util.MessageUtil;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zqc on 2015/1/21.
 */
public class BaseController {

    @Autowired
    public MessageUtil messageUtil;

    /**
     * The constant MAPPING.
     */
    public static final SerializeConfig MAPPING;
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        MAPPING = new SerializeConfig();
        MAPPING.put(Date.class, new SimpleDateFormatSerializer(FORMAT));
    }

    /**
     * Init binder.
     *
     * @param binder the binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    /**
     * Gets parameter.
     *
     * @param request the request
     * @return the parameter
     */
    protected String getParameter(HttpServletRequest request){
        String p = "";
        try {
            request.setCharacterEncoding("UTF-8");
            BufferedReader buff = null;
            StringBuffer str = null;
            str = new StringBuffer();
            String s = null;
            buff = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
            while ((s = buff.readLine()) != null){
                str.append(s);
            }
            p = str.toString();
            if(StringUtil.isEmpty(p)){
                p = "{}";
            }
        } catch (IOException e) {
            printLogger(e);
        }

        return p;
    }

    /**
     * 获取认证
     * @param request
     * @return
     */
    protected AuthHeaderBean getAuthHeader(HttpServletRequest request){
        AuthHeaderBean authHeaderBean = new AuthHeaderBean();
        String auth = request.getHeader("Authorization");
//        if(getPlatformHeader(request).equals(PrefixCode.API_HEAD_WEB)){
//            String decodedTicket = DESUtils.decrypt(auth, PropertiesUtil.getString("sso.secretKey"));
//            StudyLogger.recBusinessLog("auth:[" + auth + "] encode[" + decodedTicket + "]");
//            String[] webs = decodedTicket.split(SplitCode.SPLIT_EQULE);
//            authHeaderBean.setUserId(Integer.parseInt(webs[0]));
//        }else{
            String encode= StringUtil.getFromBASE64(auth);
            StudyLogger.recBusinessLog("auth:[" + auth + "] encode[" + encode + "]");
            String[] strs = encode.split(SplitCode.SPLIT_EQULE);
            authHeaderBean.setUserId(Integer.parseInt(strs[0]));
            authHeaderBean.setEncode(encode);

//        }
        return authHeaderBean;
    }



    /**
     * 获取platform
     * @param request
     * @return
     */
    protected String getPlatformHeader(HttpServletRequest request) {
        StudyLogger.recBusinessLog("platform:"+request.getHeader("platform"));
        return request.getHeader("platform");
    }
    /**
     * Print logger.
     *
     * @param message the message
     */
    public void printLogger(String message) {
        StudyLogger.recBusinessLog(Level.INFO, message);
    }

    /**
     * Print logger.
     *
     * @param e the e
     */
    public void printLogger(Exception e) {
        StudyLogger.recBusinessLog(Level.ERROR, e.getMessage(), e);
    }
}
