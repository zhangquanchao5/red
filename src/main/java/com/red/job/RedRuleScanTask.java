package com.red.job;



import com.alibaba.fastjson.JSON;
import com.red.common.code.ErrorCode;
import com.red.common.code.PrefixCode;
import com.red.common.code.SplitCode;
import com.red.common.RedLogger;
import com.red.common.StringUtil;
import com.red.common.apibean.ApiResponseMessage;
import com.red.common.bean.OrgRechargeBean;
import com.red.common.http.HttpSendResult;
import com.red.common.http.HttpUtil;
import com.red.common.util.DESUtils;
import com.red.common.util.EncryptUtil;
import com.red.common.util.PropertiesUtil;
import com.red.dao.OrgRuleMapper;
import com.red.dao.RefundHistoryMapper;
import com.red.dao.UserHistoryMapper;
import com.red.domain.OrgRule;
import com.red.domain.RefundHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by huichao on 2015/4/30.
 */
@Service
public class RedRuleScanTask {

    @Autowired
    private OrgRuleMapper orgRuleMapper;

    @Autowired
    private UserHistoryMapper userHistoryMapper;

    @Autowired
    private RefundHistoryMapper refundHistoryMapper;


    /**
     * Do scan.
     */
    public void doScan(){
        //第一步获取所有过期并且有效的红包规则
        RedLogger.recBusinessLog("RedRuleScanTask start ...........");
        List<OrgRule> orgRuleList=orgRuleMapper.findExpireAndRunList();
        if(orgRuleList!=null&&orgRuleList.size()>0){
            for(OrgRule orgRule:orgRuleList){
                //第一步获取总计领取的红包
                int useRed=userHistoryMapper.findRedIdCount(orgRule.getId());
                int total=orgRule.getAveragePrice()*orgRule.getRedCount();
                if(total>useRed){
                    int refund=0;
                    int costFund=0;
                   //判断是否需要退换佣金
                    if(!StringUtil.isEmpty(orgRule.getCost())&&orgRule.getCost()>0){
                        costFund=(int)(((double)orgRule.getCost()/total)*(total-useRed));
                    }
                    //退到余额账户的钱数
                    refund=costFund+total-useRed;
                    RedLogger.recBusinessLog("RedRuleScanTask money refund [orgId="+orgRule.getOrgId()+"] [refund="+refund+"]");
                    OrgRechargeBean orgRechargeBean=new OrgRechargeBean();
                    orgRechargeBean.setAccountBIllType(PrefixCode.API_ACCOUNT_RED_CODE);
                    orgRechargeBean.setOrgId(DESUtils.encrypt(orgRule.getOrgId().toString(), DESUtils.secretKey));
                    orgRechargeBean.setMoney(DESUtils.encrypt(refund + SplitCode.SPLIT_BLANK, DESUtils.secretKey));
                    orgRechargeBean.setAuthKey(EncryptUtil.encrypt(orgRule.getOrgId() + SplitCode.SPLIT_BLANK + refund, EncryptUtil.MD5));

                    HttpSendResult httpSendResult= HttpUtil.executePost(PropertiesUtil.getString("USER.HEADER.ORG.RECHARGE"), JSON.toJSONString(orgRechargeBean),PrefixCode.API_CONTENT_TYPE);
                    if(httpSendResult.getStatusCode()==200){
                        ApiResponseMessage apiResponseMessage=JSON.parseObject(httpSendResult.getResponse(),ApiResponseMessage.class);
                        if(apiResponseMessage.getCode().equals(ErrorCode.SUCCESS+SplitCode.SPLIT_BLANK)){
                            RedLogger.recBusinessLog("RedRuleScanTask sucess orgId:"+orgRule.getOrgId());
                            orgRuleMapper.updateStatusHasScan(orgRule.getId());
                            //增加退款记录
                            RefundHistory refundHistory=new RefundHistory();
                            refundHistory.setCostMoney(costFund);
                            refundHistory.setCreateTime(new Date());
                            refundHistory.setOrgId(orgRule.getOrgId());
                            refundHistory.setRedId(orgRule.getId());
                            refundHistory.setRedMoney(total-useRed);

                            refundHistoryMapper.insert(refundHistory);
                        }else{
                            RedLogger.recBusinessLog("RedRuleScanTask exception"+JSON.toJSONString(httpSendResult));
                        }
                    }else{
                        RedLogger.recBusinessLog("RedRuleScanTask exception"+JSON.toJSONString(httpSendResult));
                    }
                }else{
                    //更改状态为2 已扫描
                    orgRuleMapper.updateStatusHasScan(orgRule.getId());
                }
            }
        }
        RedLogger.recBusinessLog("RedRuleScanTask over ...........");
    }

}
