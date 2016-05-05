package com.red.model;

import java.util.Date;

public class RechargeHistory {
    private Integer id;

    private Integer orgId;

    private Integer redId;

    private Integer redNums;

    private Integer redMoney;

    private Integer costMoney;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getRedId() {
        return redId;
    }

    public void setRedId(Integer redId) {
        this.redId = redId;
    }

    public Integer getRedNums() {
        return redNums;
    }

    public void setRedNums(Integer redNums) {
        this.redNums = redNums;
    }

    public Integer getRedMoney() {
        return redMoney;
    }

    public void setRedMoney(Integer redMoney) {
        this.redMoney = redMoney;
    }

    public Integer getCostMoney() {
        return costMoney;
    }

    public void setCostMoney(Integer costMoney) {
        this.costMoney = costMoney;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}