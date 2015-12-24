package com.red.dao;

import com.red.domain.RefundHistory;

/**
 * The interface Refund history mapper.
 */
public interface RefundHistoryMapper {
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
    int insert(RefundHistory record);

    /**
     * Insert selective int.
     *
     * @param record the record
     * @return the int
     */
    int insertSelective(RefundHistory record);

    /**
     * Select by primary key refund history.
     *
     * @param id the id
     * @return the refund history
     */
    RefundHistory selectByPrimaryKey(Integer id);

    /**
     * Update by primary key selective int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKeySelective(RefundHistory record);

    /**
     * Update by primary key int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKey(RefundHistory record);
}