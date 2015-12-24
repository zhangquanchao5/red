package com.red.domain;

import java.util.Date;

/**
 * The type Refund history.
 */
public class RefundHistory {
    private Integer id;

    private Integer orgId;

    private Integer redId;

    private Integer redMoney;

    private Integer costMoney;

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
     * Gets org id.
     *
     * @return the org id
     */
    public Integer getOrgId() {
        return orgId;
    }

    /**
     * Sets org id.
     *
     * @param orgId the org id
     */
    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
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
     * Gets red money.
     *
     * @return the red money
     */
    public Integer getRedMoney() {
        return redMoney;
    }

    /**
     * Sets red money.
     *
     * @param redMoney the red money
     */
    public void setRedMoney(Integer redMoney) {
        this.redMoney = redMoney;
    }

    /**
     * Gets cost money.
     *
     * @return the cost money
     */
    public Integer getCostMoney() {
        return costMoney;
    }

    /**
     * Sets cost money.
     *
     * @param costMoney the cost money
     */
    public void setCostMoney(Integer costMoney) {
        this.costMoney = costMoney;
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