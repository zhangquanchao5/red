package com.red.domain;

import java.util.Date;

/**
 * The type User history.
 */
public class UserHistory {
    private Integer id;

    private Integer redId;

    private String mobile;

    private Integer money;

    private Byte type;

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
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * Gets money.
     *
     * @return the money
     */
    public Integer getMoney() {
        return money;
    }

    /**
     * Sets money.
     *
     * @param money the money
     */
    public void setMoney(Integer money) {
        this.money = money;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Byte getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(Byte type) {
        this.type = type;
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