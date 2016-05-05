package com.red.service;

import com.red.common.apibean.UserHistoryPageReq;
import com.red.common.apibean.response.OrgOrRedResponse;
import com.red.common.apibean.response.StatisticsComResponse;
import com.red.common.apibean.response.StatisticsRedResponse;
import com.red.common.apibean.response.UserHistoryResponse;
import com.red.common.page.PageResponse;
import com.red.domain.UserHistory;

import java.util.List;

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
     * @param mobile the mobile
     * @return the page response
     */
    PageResponse findUserHistorys(UserHistoryPageReq userHistoryPageReq,String mobile);

    /**
     * Update by mobile.
     *
     * @param mobile the mobile
     * @param id the id
     * @return the list
     * @throws Exception the exception
     */
    List<UserHistory> updateByMobile(String mobile,Integer id) throws Exception;

    /**
     * Find user historys.
     *
     * @param userHistoryPageReq the user history page req
     * @return the page response
     */
    PageResponse  statisticsHistory(UserHistoryPageReq userHistoryPageReq);

    /**
     * Statistics history detail.
     *
     * @param redId the red id
     * @param mobile the mobile
     * @param type the type
     * @return the page response
     */
    List<UserHistoryResponse>  statisticsHistoryDetail(Integer redId, String mobile,String type);

    /**
     * Statistics history count.
     *
     * @param userHistoryPageReq the user history page req
     * @return the org or red response
     */
    OrgOrRedResponse statisticsHistoryCount(UserHistoryPageReq userHistoryPageReq);

    /**
     * Find statistics history all.
     *
     * @param userHistoryPageReq the user history page req
     * @return the statistics com response
     */
    StatisticsComResponse findStatisticsHistoryAll(UserHistoryPageReq userHistoryPageReq);
}
