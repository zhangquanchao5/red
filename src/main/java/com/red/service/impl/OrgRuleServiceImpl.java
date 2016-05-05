package com.red.service.impl;

import com.red.common.algorithm.RedGenerateUtil;
import com.red.common.apibean.OrgRuleReq;
import com.red.common.apibean.RedAddReq;
import com.red.common.apibean.response.OrgRuleResponse;
import com.red.common.bean.ResponseMessage;
import com.red.common.code.EntityCode;
import com.red.common.code.ErrorCode;
import com.red.common.exception.CustomException;
import com.red.common.util.MessageUtil;
import com.red.dao.OrgRuleMapper;
import com.red.dao.RechargeHistoryMapper;
import com.red.dao.RedDetailMapper;
import com.red.domain.OrgRule;
import com.red.domain.RedDetail;
import com.red.model.RechargeHistory;
import com.red.service.OrgRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Star on 2015/12/24.
 */
@Service
public class OrgRuleServiceImpl implements OrgRuleService {
    @Autowired
    private MessageUtil messageUtil;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private OrgRuleMapper orgRuleMapper;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private RedDetailMapper redDetailMapper;

    @Autowired
    private RechargeHistoryMapper rechargeHistoryMapper;

    @Override
    public Integer createOrgRule(OrgRule orgRule) throws Exception {
        //save Red
        checkOrgRuleFields(orgRule);
        orgRule.setCreateTime(new Date());
        orgRule.setStatus(EntityCode.RED_STATUS_VALIDE);
        orgRule.setReceiveRedSeq(orgRule.getRedCount());
        orgRuleMapper.insertSelective(orgRule);

        //save RedDetail
        RedDetail redDetail;
        int[] moneyList = RedGenerateUtil.generate(orgRule);
        for (int i = 0;i < moneyList.length;i++) {
            redDetail = new RedDetail();
            redDetail.setCreateTime(new Date());
            redDetail.setRedId(orgRule.getId());
            redDetail.setMoney(moneyList[i]);
            redDetail.setIndex(i+1);
            redDetailMapper.insertSelective(redDetail);
        }

        RechargeHistory rechargeHistory=new RechargeHistory();
        rechargeHistory.setOrgId(orgRule.getOrgId());
        rechargeHistory.setCostMoney(orgRule.getCost());
        rechargeHistory.setRedId(orgRule.getId());
        rechargeHistory.setCreateTime(new Date());
        rechargeHistory.setRedNums(orgRule.getRedCount());
        rechargeHistory.setRedMoney(orgRule.getRedCount()*orgRule.getAveragePrice());
        rechargeHistoryMapper.insertSelective(rechargeHistory);

        return orgRule.getId();
    }

    public ResponseMessage updateOrgRule(RedAddReq redAddReq, ResponseMessage message ) throws Exception{
        //判断红包规则是否存在
        OrgRule orgRule=orgRuleMapper.selectByPrimaryKey(redAddReq.getRedId());
        if(orgRule==null){
            message.setCode(ErrorCode.RED_INVALIDE);
            return message;
        }
        //判断是否过期
        if(orgRule.getStatus()!=EntityCode.RED_STATUS_VALIDE){
            message.setCode(ErrorCode.RED_INVALIDE);
            return message;
        }
        //判断平均单价是否相同
        if(redAddReq.getTotal()/redAddReq.getRedCount()!=orgRule.getAveragePrice().intValue()){
            message.setCode(ErrorCode.RED_PRICE_NO_SAME);
            return message;
        }

        //创建红包明细
        RedDetail redDetail;
        int[] moneyList = RedGenerateUtil.generate(orgRule.getAveragePrice().intValue(),redAddReq.getRedCount().intValue(),redAddReq.getTotal().intValue(),orgRule.getType());
        for (int i = 0;i < moneyList.length;i++) {
            redDetail = new RedDetail();
            redDetail.setCreateTime(new Date());
            redDetail.setRedId(orgRule.getId());
            redDetail.setMoney(moneyList[i]);
            redDetail.setIndex(i+1+orgRule.getRedCount());
            redDetailMapper.insertSelective(redDetail);
        }

        //修改红包规则
        orgRule.setRedCount(orgRule.getRedCount()+redAddReq.getRedCount());
        orgRule.setCost(orgRule.getCost()+redAddReq.getCost());
        orgRule.setReceiveRedSeq(orgRule.getReceiveRedSeq()+redAddReq.getRedCount());
        orgRuleMapper.updateByPrimaryKeySelective(orgRule);



        RechargeHistory rechargeHistory=new RechargeHistory();
        rechargeHistory.setOrgId(orgRule.getOrgId());
        rechargeHistory.setCostMoney(redAddReq.getCost());
        rechargeHistory.setRedId(redAddReq.getRedId());
        rechargeHistory.setCreateTime(new Date());
        rechargeHistory.setRedNums(redAddReq.getRedCount());
        rechargeHistory.setRedMoney(redAddReq.getRedCount()*orgRule.getAveragePrice());
        rechargeHistoryMapper.insertSelective(rechargeHistory);

        message.setData(orgRule);
        message.setCode(ErrorCode.SUCCESS);
        return message;
    }

    @Override
    public List<OrgRule> getOrgRules(Integer orgId) throws Exception {
        if (null == orgId) {
            throw new CustomException(messageUtil.getMessage("msg.parameter.notEnough", "orgId"), ErrorCode.PARAMETER_NOT_ENOUGH);
        }

        return orgRuleMapper.findByOrg(orgId);
    }

    @Override
    public OrgRule getOrgRule(Integer id) throws Exception {
        if (null == id) {
            throw new CustomException(messageUtil.getMessage("msg.parameter.notEnough", "id"), ErrorCode.PARAMETER_NOT_ENOUGH);
        }

        return orgRuleMapper.selectByPrimaryKey(id);
    }

   public List<OrgRuleResponse> findByQuery(OrgRuleReq orgRuleReq) throws Exception{
        if (null == orgRuleReq.getId()) {
            throw new CustomException(messageUtil.getMessage("msg.parameter.notEnough", "id"), ErrorCode.PARAMETER_NOT_ENOUGH);
        }

       return orgRuleMapper.findByQuery(orgRuleReq);
    }

    private void checkOrgRuleFields(OrgRule orgRule) throws Exception {
        if (null == orgRule.getOrgId() || orgRule.getOrgId() < 0) {
            throw new CustomException(messageUtil.getMessage("msg.parameter.notEnough", "orgId"), ErrorCode.PARAMETER_NOT_ENOUGH);
        }

        if (null == orgRule.getUserId() || orgRule.getUserId() < 0) {
            throw new CustomException(messageUtil.getMessage("msg.parameter.notEnough", "userId"), ErrorCode.PARAMETER_NOT_ENOUGH);
        }
        if (null == orgRule.getRedCount() || orgRule.getRedCount() < 0) {
            throw new CustomException(messageUtil.getMessage("msg.parameter.notEnough", "redCount"), ErrorCode.PARAMETER_NOT_ENOUGH);
        }

        if (null == orgRule.getAveragePrice() || orgRule.getAveragePrice() < 0) {
            throw new CustomException(messageUtil.getMessage("msg.parameter.notEnough", "averagePrice"), ErrorCode.PARAMETER_NOT_ENOUGH);
        }

        if (null == orgRule.getExpireTime()) {
            throw new CustomException(messageUtil.getMessage("msg.parameter.notEnough", "expireTime"), ErrorCode.PARAMETER_NOT_ENOUGH);
        }

        if (null == orgRule.getType()) {
            throw new CustomException(messageUtil.getMessage("msg.parameter.notEnough", "type"), ErrorCode.PARAMETER_NOT_ENOUGH);
        }

        /*if (null == orgRule.getReceiveRedSeq() || orgRule.getReceiveRedSeq() < 0) {
            throw new CustomException(messageUtil.getMessage("msg.parameter.notEnough", "receiveRedSeq"), ErrorCode.PARAMETER_NOT_ENOUGH);
        }*/

        if (null == orgRule.getCost() || orgRule.getCost() < 0) {
            throw new CustomException(messageUtil.getMessage("msg.parameter.notEnough", "cost"), ErrorCode.PARAMETER_NOT_ENOUGH);
        }

        if (orgRule.getAveragePrice() < 1) {
            throw new CustomException(messageUtil.getMessage("msg.parameter.invalid", "averagePrice="+orgRule.getAveragePrice()), ErrorCode.PARAMETER_NOT_ENOUGH);
        }

        if (orgRule.getExpireTime().before(new Date())) {
            throw new CustomException(messageUtil.getMessage("msg.parameter.invalid", "expireTime < today"), ErrorCode.PARAMETER_NOT_ENOUGH);
        }
    }
}
