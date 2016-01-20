package com.red.common.apibean;

/**
 * Created by huichao on 2016/1/20.
 */
public class OrgRuleReq {
    private Integer[] id;
    private Integer type;
    private String createTimeBegin;
    private String createTimeEnd;
    private Integer status;

    public Integer[] getId() {
        return id;
    }

    public void setId(Integer[] id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(String createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public String getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(String createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
