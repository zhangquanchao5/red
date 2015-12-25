package com.red.service;

import com.red.common.apibean.UserHistoryPageReq;
import com.red.common.page.PageResponse;

/**
 * Created by Star on 2015/12/24.
 */
public interface UserHistoryService {

    PageResponse findUserHistorys(UserHistoryPageReq userHistoryPageReq,String mobile);
}
