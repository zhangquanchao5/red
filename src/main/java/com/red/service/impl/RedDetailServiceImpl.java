package com.red.service.impl;

import com.alibaba.fastjson.JSON;
import com.red.common.RedLogger;
import com.red.common.apibean.ApiResponseMessage;
import com.red.common.apibean.response.RedDetailResponse;
import com.red.common.bean.RechargeReq;
import com.red.common.code.EntityCode;
import com.red.common.code.ErrorCode;
import com.red.common.code.PrefixCode;
import com.red.common.code.SplitCode;
import com.red.common.exception.CustomException;
import com.red.common.http.HttpSendResult;
import com.red.common.http.HttpUtil;
import com.red.common.sms.SendSm;
import com.red.common.sms.SmsResponse;
import com.red.common.util.DESUtils;
import com.red.common.util.EncryptUtil;
import com.red.common.util.MessageUtil;
import com.red.common.util.PropertiesUtil;
import com.red.dao.OrgRuleMapper;
import com.red.dao.RedDetailMapper;
import com.red.dao.UserHistoryMapper;
import com.red.domain.OrgRule;
import com.red.domain.RedDetail;
import com.red.domain.UserHistory;
import com.red.service.RedDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Star on 2015/12/24.
 */
@Service
public class RedDetailServiceImpl implements RedDetailService {
    @Autowired
    private MessageUtil messageUtil;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private OrgRuleMapper orgRuleMapper;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private RedDetailMapper redDetailMapper;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private UserHistoryMapper userHistoryMapper;

    @Override
    public RedDetail getRedMoney(Integer redId) throws Exception {
        OrgRule orgRule = orgRuleMapper.selectByPrimaryKey(redId);
        if (null == orgRule || orgRule.getStatus() != EntityCode.RED_STATUS_VALIDE) {
            throw new CustomException(messageUtil.getMessage("msg.parameter.invalid", "orgRule="+redId), ErrorCode.PARAMETER_NOT_ENOUGH);
        }

        if (!orgRule.getStatus().equals(EntityCode.RED_STATUS_VALIDE)) {
            throw new CustomException(messageUtil.getMessage("msg.red.invalid", "orgRule="+redId), ErrorCode.RED_INVALIDE);
        }

        if (orgRule.getExpireTime().getTime()<new Date().getTime()) {
            throw new CustomException(messageUtil.getMessage("msg.redDetail.expire.time", "orgRule="+redId), ErrorCode.RED_EXPIRE_DATE);
        }

        if(orgRule.getReceiveRedSeq()==0){
            throw new CustomException(messageUtil.getMessage("msg.redDetail.over", "orgRule="+redId), ErrorCode.RED_OVER_DATE);
        }

        RedDetail redDetail = redDetailMapper.getByRedIdAndIndex(orgRule.getId(), orgRule.getReceiveRedSeq());

        if (null == redDetail) {
            throw new CustomException(messageUtil.getMessage("msg.redDetail.invalid", "orgRule="+orgRule.getId() + ";receiveRedSeq="+orgRule.getReceiveRedSeq()), ErrorCode.RED_DETAIL_INVALIDE);
        }

        return redDetail;
    }

    @Override
    public synchronized RedDetailResponse saveHistory(Integer redId, String phone, Integer type,RedDetailResponse redDetailResponse) throws Exception{
        OrgRule orgRule = orgRuleMapper.selectByPrimaryKey(redId);
        if (null == orgRule || orgRule.getStatus() != EntityCode.RED_STATUS_VALIDE) {
            throw new CustomException(messageUtil.getMessage("msg.parameter.invalid", "orgRule="+redId), ErrorCode.PARAMETER_NOT_ENOUGH);
        }

        if (!orgRule.getStatus().equals(EntityCode.RED_STATUS_VALIDE)) {
            throw new CustomException(messageUtil.getMessage("msg.red.invalid", "orgRule="+redId), ErrorCode.RED_INVALIDE);
        }

        if (orgRule.getExpireTime().getTime()<new Date().getTime()) {
            throw new CustomException(messageUtil.getMessage("msg.redDetail.expire.time", "orgRule="+redId), ErrorCode.RED_EXPIRE_DATE);
        }

        if(orgRule.getReceiveRedSeq()==0){
            throw new CustomException(messageUtil.getMessage("msg.redDetail.over", "orgRule="+redId), ErrorCode.RED_OVER_DATE);
        }

        RedDetail redDetail = redDetailMapper.getByRedIdAndIndex(orgRule.getId(), orgRule.getReceiveRedSeq());

        if (null == redDetail) {
            throw new CustomException(messageUtil.getMessage("msg.redDetail.invalid", "orgRule="+orgRule.getId() + ";receiveRedSeq="+orgRule.getReceiveRedSeq()), ErrorCode.RED_DETAIL_INVALIDE);
        }


        UserHistory userHistory = new UserHistory();
        userHistory.setRedId(redDetail.getRedId());
        userHistory.setMobile(phone);
        userHistory.setMoney(redDetail.getMoney());
        userHistory.setCreateTime(new Date());
        userHistory.setType(type.byteValue());
       // userHistory.setReceive(EntityCode.USER_RED_HASNO_RECEIVE);
        //等拆取记上后，下面的逻辑隐藏，上面放开
        userHistory.setReceive(EntityCode.USER_RED_HAS_RECEIVE);
        userHistory.setTakeTime(new Date());

        userHistoryMapper.insert(userHistory);

        orgRule = orgRuleMapper.selectByPrimaryKey(redDetail.getRedId());
        int receiveRedSeq = orgRule.getReceiveRedSeq();
        receiveRedSeq--;
        orgRule.setReceiveRedSeq(receiveRedSeq);
        orgRuleMapper.updateByPrimaryKeySelective(orgRule);

        redDetailMapper.deleteByPrimaryKey(redDetail.getId());

        redDetailResponse.setMoney(redDetail.getMoney());
        redDetailResponse.setRecordId(userHistory.getId());

        //账户充值
        RechargeReq rechargeReq = new RechargeReq();
        rechargeReq.setAccountBIllType(PrefixCode.API_ACCOUNT_RED_CODE);
        rechargeReq.setMobile(DESUtils.encrypt(phone.trim(), DESUtils.secretKey));
        rechargeReq.setMoney(DESUtils.encrypt(redDetail.getMoney() + SplitCode.SPLIT_BLANK, DESUtils.secretKey));
        rechargeReq.setAuthKey(EncryptUtil.encrypt(phone.trim() + SplitCode.SPLIT_BLANK + redDetail.getMoney(), EncryptUtil.MD5));
        //给用户充值
        HttpSendResult httpSendResult = HttpUtil.executePost(PropertiesUtil.getString("USER.HEADER.RECHARGE"), JSON.toJSONString(rechargeReq), PrefixCode.API_CONTENT_TYPE);
        if (httpSendResult.getStatusCode() == 200) {
            ApiResponseMessage apiResponseMessage = JSON.parseObject(httpSendResult.getResponse(), ApiResponseMessage.class);
            if (apiResponseMessage.getCode().equals(ErrorCode.SUCCESS + SplitCode.SPLIT_BLANK)) {
                RedLogger.recBusinessLog("RedDetailServiceImpl saveHistory success[" + JSON.toJSONString(httpSendResult) + "] [REDFETAIL=" + JSON.toJSONString(redDetail) + "] [phone=" + phone);
                //判断是否第一次获取这个红包规则的分享者，是的话发送短信,此处暂时隐藏，等短信报备了放开
                this.sendSms(redDetail.getRedId(), phone);
            } else {
                RedLogger.recBusinessLog("RedDetailServiceImpl saveHistory exception[" + JSON.toJSONString(httpSendResult) + "] [REDFETAIL=" + JSON.toJSONString(redDetail) + "] [phone=" + phone + "]");
            }
        } else {
            RedLogger.recBusinessLog("RedDetailServiceImpl saveHistory exception[" + JSON.toJSONString(httpSendResult) + "] [REDFETAIL=" + JSON.toJSONString(redDetail) + "] [phone=" + phone + "] ");
        }
        return  redDetailResponse;

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


    public void saveTest() throws Exception{
        UserHistory userHistory = new UserHistory();
        userHistory.setRedId(2);
        userHistory.setMobile(2 + "");
        userHistory.setMoney(2);
        userHistory.setCreateTime(new Date());
        userHistory.setType((byte) 1);
        userHistoryMapper.insert(userHistory);

//       if(1>0){
//           throw new Exception();
//       }
        userHistory = new UserHistory();
        userHistory.setRedId(3);
        userHistory.setMobile(3 + "");
        userHistory.setMoney(3);
        userHistory.setCreateTime(new Date());
        userHistory.setType((byte)1);
        userHistoryMapper.insert(userHistory);


    }
}
