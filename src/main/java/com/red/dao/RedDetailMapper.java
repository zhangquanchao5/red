package com.red.dao;

import com.red.domain.RedDetail;
import org.apache.ibatis.annotations.Param;

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

    /**
     * Gets by red id and index.
     *
     * @param redId the red id
     * @param index the index
     * @return the red detail
     */
    RedDetail getByRedIdAndIndex(@Param("redId") Integer redId, @Param("index") Integer index);
}