package com.red.service.impl;

import com.red.common.code.ErrorCode;
import com.red.common.exception.CustomException;
import com.red.common.util.MessageUtil;
import com.red.dao.OrgRuleMapper;
import com.red.domain.OrgRule;
import com.red.service.OrgRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Override
    public Integer createOrgRule(OrgRule orgRule) throws Exception {
        checkOrgRuleFields(orgRule);

        orgRuleMapper.insertSelective(orgRule);
        return null;
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

        if (null == orgRule.getReceiveRedSeq() || orgRule.getReceiveRedSeq() < 0) {
            throw new CustomException(messageUtil.getMessage("msg.parameter.notEnough", "receiveRedSeq"), ErrorCode.PARAMETER_NOT_ENOUGH);
        }

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
