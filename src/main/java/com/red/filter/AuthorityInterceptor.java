package com.red.filter;



import com.alibaba.fastjson.JSON;
import com.red.common.StringUtil;
import com.red.common.apibean.response.CommonResponse;
import com.red.common.bean.ResponseMessage;
import com.red.common.code.ErrorCode;
import com.red.common.http.HttpSendResult;
import com.red.common.http.HttpUtil;
import com.red.common.util.PropertiesUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * The type Authority interceptor.
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

    /**
     * Instantiates a new Authority interceptor.
     */
    public AuthorityInterceptor() {
    }

    /**
     * preHandle
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws IOException {

        String platform=request.getHeader("platform");
        String authorization=request.getHeader("Authorization");
        ResponseMessage responseMessage=new ResponseMessage();
        if(StringUtil.isEmpty(platform)||StringUtil.isEmpty(authorization)){
            responseMessage.setCode(ErrorCode.USER_NO_HEADER );
            response.getWriter().write(JSON.toJSONString(responseMessage));
            return false;
        }
        Map<String,String> map=new HashMap<String,String>();
        map.put("auth_token",authorization);
        HttpSendResult httpSendResult= HttpUtil.executePostAuth(PropertiesUtil.getString("USER.HEADER.VALIDATE"),JSON.toJSONString(map),platform, authorization);
        if(httpSendResult.getStatusCode()==200){
            CommonResponse commonResponse=JSON.parseObject(httpSendResult.getResponse(), CommonResponse.class);
            if(!commonResponse.getCode().equals(ErrorCode.SUCCESS+"")){
                responseMessage.setCode(Integer.parseInt(commonResponse.getCode()));
                response.getWriter().write(JSON.toJSONString(responseMessage));
                return false;
            }
        }else{
            responseMessage.setCode(ErrorCode.ERROR );
            response.getWriter().write(JSON.toJSONString(responseMessage));
            return false;
        }

        return true;
    }

}
