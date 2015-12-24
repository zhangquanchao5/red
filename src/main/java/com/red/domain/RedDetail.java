package com.red.domain;

import java.util.Date;

/**
 * The type Red detail.
 */
public class RedDetail {
    private Integer id;

    private Integer redId;

    private Integer money;

    private Integer index;

    private Date createTime;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets red id.
     *
     * @return the red id
     */
    public Integer getRedId() {
        return redId;
    }

    /**
     * Sets red id.
     *
     * @param redId the red id
     */
    public void setRedId(Integer redId) {
        this.redId = redId;
    }

    /**
     * Gets money.
     *
     * @return the money
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * Sets money.
     *
     * @param money the money
     */
    public void setMoney(Integer money) {
        this.money = money;
    }

    /**
     * Gets index.
     *
     * @return the index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * Sets index.
     *
     * @param index the index
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * Gets create time.
     *
     * @return the create time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Sets create time.
     *
     * @param createTime the create time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}