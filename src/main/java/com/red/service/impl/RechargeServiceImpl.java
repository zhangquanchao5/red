package com.red.service.impl;

import com.red.dao.RechargeHistoryMapper;
import com.red.model.RechargeHistory;
import com.red.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huichao on 2016/4/7.
 */
@Service
public class RechargeServiceImpl implements RechargeService {
    @Autowired
    private RechargeHistoryMapper rechargeHistoryMapper;

    public List<RechargeHistory> findByRedId(Integer redId){
        return rechargeHistoryMapper.findByRedId(redId);
    }
}
