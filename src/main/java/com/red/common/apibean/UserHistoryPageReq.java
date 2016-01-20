package com.red.common.apibean;

import com.red.common.page.PageRequest;

/**
 * Created by huichao on 2015/12/25.
 */
public class UserHistoryPageReq extends PageRequest {

    private String mobile;

    private Integer orgId;

    private Integer redId;

    private Integer type;

    private String timeBegin;

    private String timeEnd;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public UserHistoryPageReq() {
    }

    public UserHistoryPageReq(Integer redId) {
        this.redId = redId;
    }

    /**
     * Gets mobile.
     *
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets mobile.
     *
     * @param mobile the mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
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
}
