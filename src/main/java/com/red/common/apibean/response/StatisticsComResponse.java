package com.red.common.apibean.response;

import com.red.common.page.PageResponse;

import java.util.List;

/**
 * Created by huichao on 2016/3/23.
 */
public class StatisticsComResponse {

    private PageResponse pageResponse;
    private OrgOrRedResponse orgOrRedResponse;
    private Integer mobiles;
    private Integer redMoneys;
    private Integer redNums;

    public Integer getRedMoneys() {
        return redMoneys;
    }

    public void setRedMoneys(Integer redMoneys) {
        this.redMoneys = redMoneys;
    }

    public Integer getRedNums() {
        return redNums;
    }

    public void setRedNums(Integer redNums) {
        this.redNums = redNums;
    }

    public Integer getMobiles() {
        return mobiles;
    }

    public void setMobiles(Integer mobiles) {
        this.mobiles = mobiles;
    }

    public PageResponse getPageResponse() {
        return pageResponse;
    }

    public void setPageResponse(PageResponse pageResponse) {
        this.pageResponse = pageResponse;
    }

    public OrgOrRedResponse getOrgOrRedResponse() {
        return orgOrRedResponse;
    }

    public void setOrgOrRedResponse(OrgOrRedResponse orgOrRedResponse) {
        this.orgOrRedResponse = orgOrRedResponse;
    }
}
