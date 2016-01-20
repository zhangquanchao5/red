package com.red.service.impl;

import com.alibaba.fastjson.JSON;
import com.red.common.RedLogger;
import com.red.common.apibean.ApiResponseMessage;
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

        RedDetail redDetail = redDetailMapper.getByRedIdAndIndex(orgRule.getId(), orgRule.getReceiveRedSeq());

        if (null == redDetail) {
            throw new CustomException(messageUtil.getMessage("msg.redDetail.invalid", "orgRule="+orgRule.getId() + ";receiveRedSeq="+orgRule.getReceiveRedSeq()), ErrorCode.RED_DETAIL_INVALIDE);
        }

        return redDetail;
    }

    @Override
    public Integer saveHistory(RedDetail redDetail, String phone, Integer type) throws Exception {
        UserHistory userHistory = new UserHistory();
        userHistory.setRedId(redDetail.getRedId());
        userHistory.setMobile(phone);
        userHistory.setMoney(redDetail.getMoney());
        userHistory.setCreateTime(new Date());
        userHistory.setType(type.byteValue());
        userHistory.setReceive(EntityCode.USER_RED_HASNO_RECEIVE);
        userHistoryMapper.insert(userHistory);

        OrgRule orgRule = orgRuleMapper.selectByPrimaryKey(redDetail.getRedId());
        int receiveRedSeq = orgRule.getReceiveRedSeq();
        receiveRedSeq--;
        orgRule.setReceiveRedSeq(receiveRedSeq);
        orgRuleMapper.updateByPrimaryKeySelective(orgRule);

        redDetailMapper.deleteByPrimaryKey(redDetail.getId());

        return  userHistory.getId();
    }

    @Override
    public void sendSms(RedDetail redDetail, String phone) throws Exception{
       //查询总红包数
        Map<String,Object> queryMap=new HashMap<String,Object>();
        queryMap.put("redId",redDetail.getRedId());
        queryMap.put("mobile",phone);
        int reds=userHistoryMapper.findPhoneRedIdCount(queryMap);
        if(reds>1){
            //发送恭喜短信
            SmsResponse smsResponse= SendSm.sendSms(phone, messageUtil.getMessage("MSG.SMSSEND.PASSWORD.CONTENT").replace("#CODE", redDetail.getMoney().toString()));
            System.out.println(smsResponse.getCode() + ":" + smsResponse.getMsg() + ":" + smsResponse.getSmsid());
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
