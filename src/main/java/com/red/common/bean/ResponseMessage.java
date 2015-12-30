package com.red.common.bean;

import java.util.List;

/**
 * Created by huichao on 2015/7/13.
 */
public class ResponseMessage {
   // private boolean success = true;
    private int code;
    private Object data;
    private String msg;
    private List datas;

//    /**
//     * Is success boolean.
//     *
//     * @return the boolean
//     */
//    public boolean isSuccess() {
//        return success;
//    }
//
//    /**
//     * Sets success.
//     *
//     * @param success the success
//     */
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * Gets msg.
     *
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets msg.
     *
     * @param msg the msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Gets datas.
     *
     * @return the datas
     */
    public List getDatas() {
        return datas;
    }

    /**
     * Sets datas.
     *
     * @param datas the datas
     */
    public void setDatas(List datas) {
        this.datas = datas;
    }
}
