package com.red.common.page;

import java.util.List;

/**
 * Created by huichao on 2015/9/23.
 * @param <T>   the type parameter
 */
public class PageResponse<T> {
    /**
     * The List.
     */
    public List<T>  list;
    /**
     * The Total.
     */
    public Integer total;

    /**
     * The Sum.
     */
    public Integer sumMoney;

    public Integer getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Integer sumMoney) {
        this.sumMoney = sumMoney;
    }

    /**
     * Gets list.
     *
     * @return the list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * Sets list.
     *
     * @param list the list
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * Gets total.
     *
     * @return the total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * Sets total.
     *
     * @param total the total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }
}
