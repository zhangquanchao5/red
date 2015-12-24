package com.red.dao;

import com.red.domain.RedDetail;

/**
 * The interface Red detail mapper.
 */
public interface RedDetailMapper {
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
    int insert(RedDetail record);

    /**
     * Insert selective int.
     *
     * @param record the record
     * @return the int
     */
    int insertSelective(RedDetail record);

    /**
     * Select by primary key red detail.
     *
     * @param id the id
     * @return the red detail
     */
    RedDetail selectByPrimaryKey(Integer id);

    /**
     * Update by primary key selective int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKeySelective(RedDetail record);

    /**
     * Update by primary key int.
     *
     * @param record the record
     * @return the int
     */
    int updateByPrimaryKey(RedDetail record);
}