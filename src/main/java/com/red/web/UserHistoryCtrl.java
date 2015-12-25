package com.red.web;


import com.red.common.code.ErrorCode;
import com.alibaba.fastjson.JSON;
import com.red.common.RedLogger;
import com.red.common.apibean.UserHistoryPageReq;
import com.red.common.apibean.response.CommonResponse;
import com.red.common.page.PageResponse;
import com.red.common.util.ServletResponseHelper;
import com.red.domain.UserHistory;
import com.red.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Star on 2015/12/24.
 */
@Controller
@RequestMapping("/api")
public class UserHistoryCtrl {

    @Autowired
    private UserHistoryService userHistoryService;

    @RequestMapping(value = "/api/userHistory", method = RequestMethod.POST)
    public void create(UserHistory userHistory) {

    }

    @RequestMapping(value = "/api/userHistory", method = RequestMethod.PUT)
    public void update(UserHistory userHistory) {

    }

    @RequestMapping(value = "/api/userHistory/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {

    }

    @RequestMapping(value = "/api/userHistory/{id}", method = RequestMethod.GET)
    public void get(@PathVariable Integer id) {

    }

    /**
     *
     * 个人红包领取明细，没有参数查询全部，有参数按参数查询
     */
    @RequestMapping(value = "/userHistory/{id}", method = RequestMethod.GET)
    public void findHistory(@PathVariable("id") String id,UserHistoryPageReq userHistoryPageReq,HttpServletResponse response) {
        RedLogger.recBusinessLog("UserHistoryCtrl findHistory id="+id+"  content:"+ JSON.toJSONString(userHistoryPageReq));
        CommonResponse commonResponse=new CommonResponse();
        try{
            PageResponse pageResponse=userHistoryService.findUserHistorys(userHistoryPageReq,id);
            commonResponse.setCode(String.valueOf(ErrorCode.SUCCESS));
            commonResponse.setData(pageResponse);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(String.valueOf(ErrorCode.ERROR));
        }

        ServletResponseHelper.outUTF8ToJson(response, JSON.toJSONString(commonResponse));
    }
}
