package com.red.dao;

import com.red.common.apibean.UserHistoryPageReq;
import com.red.domain.UserHistory;

import java.util.List;

/**
 * The interface User history mapper.
 */
public interface UserHistoryMapper {
    /**
     * Delete by primary key int.
     *
     * @param id the id
     * @return the int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * Insert int.
     *
     * @param record the record
     * @return the int
     */
    int insert(UserHistory record);

    /**
     * Insert selective int.
     *
     * @param record the record
     * @return the int
     */
    int insertSelective(UserHistory record);

    /**
     * Select by primary key user history.
     *
     * @param id the id
     * @return the user history
     */
    UserHistory selectByPrimaryKey(Integer id);

    /**
     * Update by primary key selective int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKeySelective(UserHistory record);

    /**
     * Update by primary key int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKey(UserHistory record);

    /**
     * 获取一个红包规则领取的总红包钱数
     *
     * @param redId
     * @return the int
     */
    int findRedIdCount(Integer redId);

    /**
     * 获取查询总数
     */
    int findPageCount(UserHistoryPageReq userHistoryPageReq);

    /**
     * 获取查询列表
     */
    List<UserHistory> findPageResponse(UserHistoryPageReq userHistoryPageReq);
}