package com.red.common.sms;

/**
 * Created by huichao on 2015/7/16.
 */
public class SmsResponse {
    private String code;
    private String taskID;
    private String msg;
    private String remainpoint;
    private String successCounts;

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getSuccessCounts() {
        return successCounts;
    }

    public void setSuccessCounts(String successCounts) {
        this.successCounts = successCounts;
    }

    public String getRemainpoint() {
        return remainpoint;
    }

    public void setRemainpoint(String remainpoint) {
        this.remainpoint = remainpoint;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
