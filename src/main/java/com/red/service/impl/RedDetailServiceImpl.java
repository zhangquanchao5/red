package com.red.service.impl;

import com.red.common.code.EntityCode;
import com.red.common.code.ErrorCode;
import com.red.common.exception.CustomException;
import com.red.common.util.MessageUtil;
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
    public void saveHistory(RedDetail redDetail, String phone, Integer type) throws Exception {
        UserHistory userHistory = new UserHistory();
        userHistory.setRedId(redDetail.getRedId());
        userHistory.setMobile(phone);
        userHistory.setMoney(redDetail.getMoney());
        userHistory.setCreateTime(new Date());
        userHistory.setType(type.byteValue());
        userHistoryMapper.insert(userHistory);

        OrgRule orgRule = orgRuleMapper.selectByPrimaryKey(redDetail.getRedId());
        int receiveRedSeq = orgRule.getReceiveRedSeq();
        receiveRedSeq--;
        orgRule.setReceiveRedSeq(receiveRedSeq);
        orgRuleMapper.updateByPrimaryKeySelective(orgRule);

        redDetailMapper.deleteByPrimaryKey(orgRule.getId());
    }
}
