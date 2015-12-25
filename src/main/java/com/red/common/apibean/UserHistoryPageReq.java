package com.red.common.apibean;

import com.red.common.page.PageRequest;

/**
 * Created by huichao on 2015/12/25.
 */
public class UserHistoryPageReq extends PageRequest{

     private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private Integer orgId;

     private Integer redId;

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
}
