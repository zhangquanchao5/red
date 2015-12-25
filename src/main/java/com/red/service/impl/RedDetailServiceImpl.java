package com.red.service.impl;

import com.red.common.code.EntityCode;
import com.red.common.code.ErrorCode;
import com.red.common.exception.CustomException;
import com.red.common.util.MessageUtil;
import com.red.dao.OrgRuleMapper;
import com.red.dao.RedDetailMapper;
import com.red.domain.OrgRule;
import com.red.service.RedDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

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

    @Override
    public int getRandomMoney(Integer redId) throws Exception {
        OrgRule orgRule = orgRuleMapper.selectByPrimaryKey(redId);
        if (null == orgRule || orgRule.getStatus() != EntityCode.RED_STATUS_VALIDE) {
            throw new CustomException(messageUtil.getMessage("msg.parameter.invalid", "orgRule="+redId), ErrorCode.PARAMETER_NOT_ENOUGH);
        }
        int index = new Random().nextInt(orgRule.getReceiveRedSeq())
        redDetailMapper.getByRedIdAndIndex();
        return 0;
    }
}
