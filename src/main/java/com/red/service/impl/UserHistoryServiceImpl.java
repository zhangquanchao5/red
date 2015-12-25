package com.red.service.impl;

import com.red.common.apibean.UserHistoryPageReq;
import com.red.common.page.PageResponse;
import com.red.dao.UserHistoryMapper;
import com.red.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Star on 2015/12/24.
 */
@Service
public class UserHistoryServiceImpl implements UserHistoryService {

    @Autowired
    private UserHistoryMapper userHistoryMapper;

    public  PageResponse findUserHistorys(UserHistoryPageReq userHistoryPageReq,String mobile){
        userHistoryPageReq.setPageStart((userHistoryPageReq.getCurrentPage()-1)*userHistoryPageReq.getCurrentSize());
        userHistoryPageReq.setMobile(mobile);

        PageResponse pageResponse=new PageResponse();
        pageResponse.setTotal(userHistoryMapper.findPageCount(userHistoryPageReq));
        pageResponse.setList(userHistoryMapper.findPageResponse(userHistoryPageReq));

        return pageResponse;
    }
}
