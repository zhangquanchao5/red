package com.red.service;

import com.red.model.RechargeHistory;

import java.util.List;

/**
 * Created by huichao on 2016/4/7.
 */
public interface RechargeService {

    List<RechargeHistory> findByRedId(Integer redId);
}
