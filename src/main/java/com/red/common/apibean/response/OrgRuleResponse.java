package com.red.common.apibean.response;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * The type Org rule.
 */

public class OrgRuleResponse {
    private Integer id;

    private Integer orgId;

    private Integer redCount;

    private Integer averagePrice;

    private Boolean type;

    private Integer receiveRedSeq;

    private Integer cost;

    private Date expireTime;

    private Date createTime;

    private Integer status;

    private Integer  overplusPrice;

    public Integer getOverplusPrice() {
        return overplusPrice;
    }

    public void setOverplusPrice(Integer overplusPrice) {
        this.overplusPrice = overplusPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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
    public Integer getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(Integer cost) {
        this.cost = cost == null ? null : cost;
    }

    /**
     * Gets expire time.
     *
     * @return the expire time
     */
    public Date getExpireTime() {
        return expireTime;
    }

    /**
     * Sets expire time.
     *
     * @param expireTime the expire time
     */
    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
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