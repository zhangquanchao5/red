package com.red.common.apibean;

/**
 * Created by huichao on 2016/3/26.
 */
public class UserHistoryReq {
    private String wechatId;
    private String wechatUrl;
    private String wechatNick;

    private Integer type;

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public String getWechatUrl() {
        return wechatUrl;
    }

    public void setWechatUrl(String wechatUrl) {
        this.wechatUrl = wechatUrl;
    }

    public String getWechatNick() {
        return wechatNick;
    }

    public void setWechatNick(String wechatNick) {
        this.wechatNick = wechatNick;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
