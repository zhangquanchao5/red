package com.red.domain;

import java.util.Date;

/**
 * The type Org rule.
 */
public class OrgRule {
    private Integer id;

    private Integer orgId;

    private Integer redCount;

    private Integer averagePrice;

    private Boolean type;

    private Integer receiveRedSeq;

    private String cost;

    private Date exprieTime;

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
     * Gets red count.
     *
     * @return the red count
     */
    public Integer getRedCount() {
        return redCount;
    }

    /**
     * Sets red count.
     *
     * @param redCount the red count
     */
    public void setRedCount(Integer redCount) {
        this.redCount = redCount;
    }

    /**
     * Gets average price.
     *
     * @return the average price
     */
    public Integer getAveragePrice() {
        return averagePrice;
    }

    /**
     * Sets average price.
     *
     * @param averagePrice the average price
     */
    public void setAveragePrice(Integer averagePrice) {
        this.averagePrice = averagePrice;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Boolean getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(Boolean type) {
        this.type = type;
    }

    /**
     * Gets receive red seq.
     *
     * @return the receive red seq
     */
    public Integer getReceiveRedSeq() {
        return receiveRedSeq;
    }

    /**
     * Sets receive red seq.
     *
     * @param receiveRedSeq the receive red seq
     */
    public void setReceiveRedSeq(Integer receiveRedSeq) {
        this.receiveRedSeq = receiveRedSeq;
    }

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public String getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(String cost) {
        this.cost = cost == null ? null : cost.trim();
    }

    /**
     * Gets exprie time.
     *
     * @return the exprie time
     */
    public Date getExprieTime() {
        return exprieTime;
    }

    /**
     * Sets exprie time.
     *
     * @param exprieTime the exprie time
     */
    public void setExprieTime(Date exprieTime) {
        this.exprieTime = exprieTime;
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