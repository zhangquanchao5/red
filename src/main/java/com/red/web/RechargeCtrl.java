package com.red.web;

import com.alibaba.fastjson.JSON;
import com.red.common.RedLogger;
import com.red.common.apibean.response.CommonResponse;
import com.red.common.apibean.response.UserHistoryResponse;
import com.red.common.code.ErrorCode;
import com.red.common.util.ServletResponseHelper;
import com.red.model.RechargeHistory;
import com.red.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by huichao on 2016/4/7.
 */
@Controller
public class RechargeCtrl {

    @Autowired
    private RechargeService rechargeService;

    @RequestMapping(value = "/api/rechargeHistorys/{redId}", method = RequestMethod.GET)
    public void list(@PathVariable("redId") Integer redId, HttpServletResponse response) {
        RedLogger.recBusinessLog("RechargeCtrl list:" + JSON.toJSONString(redId));
        CommonResponse commonResponse=new CommonResponse();
        try{
            List<RechargeHistory> rechargeHistories=rechargeService.findByRedId(redId);
            commonResponse.setCode(String.valueOf(ErrorCode.SUCCESS));
            commonResponse.setData(rechargeHistories);
        }catch (Exception e){
            e.printStackTrace();
            commonResponse.setCode(String.valueOf(ErrorCode.ERROR));
        }

        ServletResponseHelper.outUTF8ToJson(response, JSON.toJSONString(commonResponse));
    }
}
