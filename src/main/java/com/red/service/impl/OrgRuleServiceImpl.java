package com.red.service.impl;

import com.red.common.algorithm.RedGenerateUtil;
import com.red.common.code.EntityCode;
import com.red.common.code.ErrorCode;
import com.red.common.exception.CustomException;
import com.red.common.util.MessageUtil;
import com.red.dao.OrgRuleMapper;
import com.red.dao.RedDetailMapper;
import com.red.domain.OrgRule;
import com.red.domain.RedDetail;
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
        return orgRule.getId();
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

    private void checkOrgRuleFields(OrgRule orgRule) throws Exception {
        if (null == orgRule.getOrgId() || orgRule.getOrgId() < 0) {
            throw new CustomException(messageUtil.getMessage("msg.parameter.notEnough", "orgId"), ErrorCode.PARAMETER_NOT_ENOUGH);
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
