package com.red.service;

import com.red.common.apibean.UserHistoryPageReq;
import com.red.common.page.PageResponse;

/**
 * Created by Star on 2015/12/24.
 */
public interface UserHistoryService {

    /**
     * Find user historys page response.
     *
     * @param userHistoryPageReq the user history page req
     * @return the page response
     */
    PageResponse findUserHistorys(UserHistoryPageReq userHistoryPageReq);

    /**
     * Find user historys page response.
     *
     * @param userHistoryPageReq the user history page req
     * @param mobile             the mobile
     * @return the page response
     */
    PageResponse findUserHistorys(UserHistoryPageReq userHistoryPageReq,String mobile);
}
