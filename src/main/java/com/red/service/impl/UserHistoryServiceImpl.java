package com.red.service.impl;

import com.alibaba.fastjson.JSON;
import com.red.common.RedLogger;
import com.red.common.apibean.ApiResponseMessage;
import com.red.common.apibean.UserHistoryPageReq;
import com.red.common.bean.RechargeReq;
import com.red.common.code.EntityCode;
import com.red.common.code.ErrorCode;
import com.red.common.code.PrefixCode;
import com.red.common.code.SplitCode;
import com.red.common.http.HttpSendResult;
import com.red.common.http.HttpUtil;
import com.red.common.page.PageResponse;
import com.red.common.sms.SendSm;
import com.red.common.sms.SmsResponse;
import com.red.common.util.DESUtils;
import com.red.common.util.EncryptUtil;
import com.red.common.util.MessageUtil;
import com.red.common.util.PropertiesUtil;
import com.red.dao.UserHistoryMapper;
import com.red.domain.RedDetail;
import com.red.domain.UserHistory;
import com.red.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Star on 2015/12/24.
 */
@Service
public class UserHistoryServiceImpl implements UserHistoryService {

    @Autowired
    private MessageUtil messageUtil;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserHistoryMapper userHistoryMapper;

    public PageResponse findUserHistorys(UserHistoryPageReq userHistoryPageReq) {
        userHistoryPageReq.setPageStart((userHistoryPageReq.getCurrentPage() - 1) * userHistoryPageReq.getCurrentSize());

        PageResponse pageResponse = new PageResponse();
        pageResponse.setTotal(userHistoryMapper.findPageCount(userHistoryPageReq));
        pageResponse.setList(userHistoryMapper.findPageResponse(userHistoryPageReq));

        return pageResponse;
    }

    public PageResponse findUserHistorys(UserHistoryPageReq userHistoryPageReq, String mobile) {
        userHistoryPageReq.setPageStart((userHistoryPageReq.getCurrentPage() - 1) * userHistoryPageReq.getCurrentSize());
        userHistoryPageReq.setMobile(mobile);

        PageResponse pageResponse = new PageResponse();
        pageResponse.setTotal(userHistoryMapper.findPageCount(userHistoryPageReq));
        pageResponse.setList(userHistoryMapper.findPageResponse(userHistoryPageReq));

        return pageResponse;
    }


    private void sendSms(Integer redId, String phone) throws Exception{
        //查询总红包数
        Map<String,Object> queryMap=new HashMap<String,Object>();
        queryMap.put("redId", redId);
        queryMap.put("mobile", phone);
        int reds=userHistoryMapper.findPhoneRedIdCount(queryMap);
        //只有第一次分享获取后发送
        if(reds==1){
            //发送恭喜短信
            SmsResponse smsResponse= SendSm.sendSms(phone, messageUtil.getMessage("MSG.SMSSEND.FAIR.CONTENT"));
            //System.out.println(smsResponse.getCode() + ":" + smsResponse.getMsg() + ":" + smsResponse.getSmsid());
            RedLogger.recBusinessLog("send mobile:"+phone +" [" +JSON.toJSONString(smsResponse)+"]");
        }
    }

    public List<UserHistory> updateByMobile(String mobile, Integer id) throws Exception {
        List<UserHistory> userHistories = new ArrayList<UserHistory>();
        int countMoney = 0;
        if (id != null) {
            UserHistory userHistory = userHistoryMapper.selectByPrimaryKey(id);
            userHistory.setReceive(EntityCode.USER_RED_HAS_RECEIVE);
            userHistory.setTakeTime(new Date());

            userHistories.add(userHistory);
            countMoney = userHistory.getMoney();
            userHistoryMapper.updateByPrimaryKeySelective(userHistory);
            //判断是否第一次获取这个红包规则的分享者，是的话发送短信,此处暂时隐藏，等短信报备了放开
            this.sendSms(userHistory.getRedId(),mobile);
        } else {
            userHistories = userHistoryMapper.findNoReceiveList(mobile);
            for (UserHistory userHistory : userHistories) {
                userHistory.setReceive(EntityCode.USER_RED_HAS_RECEIVE);
                userHistory.setTakeTime(new Date());
                countMoney = countMoney + userHistory.getMoney().intValue();
                userHistoryMapper.updateByPrimaryKeySelective(userHistory);
                this.sendSms(userHistory.getRedId(),mobile);
            }

        }

        RechargeReq rechargeReq = new RechargeReq();
        rechargeReq.setAccountBIllType(PrefixCode.API_ACCOUNT_RED_CODE);
        rechargeReq.setMobile(DESUtils.encrypt(mobile.trim(), DESUtils.secretKey));
        rechargeReq.setMoney(DESUtils.encrypt(countMoney + SplitCode.SPLIT_BLANK, DESUtils.secretKey));
        rechargeReq.setAuthKey(EncryptUtil.encrypt(mobile.trim() + SplitCode.SPLIT_BLANK + countMoney, EncryptUtil.MD5));
        //给用户充值
        HttpSendResult httpSendResult = HttpUtil.executePost(PropertiesUtil.getString("USER.HEADER.RECHARGE"), JSON.toJSONString(rechargeReq), PrefixCode.API_CONTENT_TYPE);
        if (httpSendResult.getStatusCode() == 200) {
            ApiResponseMessage apiResponseMessage = JSON.parseObject(httpSendResult.getResponse(), ApiResponseMessage.class);
            if (apiResponseMessage.getCode().equals(ErrorCode.SUCCESS + SplitCode.SPLIT_BLANK)) {
                RedLogger.recBusinessLog("RedDetailServiceImpl saveHistory success[" + JSON.toJSONString(httpSendResult) + "] [REDFETAIL=" + JSON.toJSONString(userHistories) + "] [phone=" + mobile);
            } else {
                RedLogger.recBusinessLog("RedDetailServiceImpl saveHistory exception[" + JSON.toJSONString(httpSendResult) + "] [REDFETAIL=" + JSON.toJSONString(userHistories) + "] [phone=" + mobile + "]");
                // throw new CustomException(messageUtil.getMessage("msg.process.fail"), ErrorCode.ERROR);
            }
        } else {
            RedLogger.recBusinessLog("RedDetailServiceImpl saveHistory exception[" + JSON.toJSONString(httpSendResult) + "] [REDFETAIL=" + JSON.toJSONString(userHistories) + "] [phone=" + mobile + "] ");
            // throw new CustomException(messageUtil.getMessage("msg.process.fail"), ErrorCode.ERROR);
        }

        return userHistories;
    }
}
