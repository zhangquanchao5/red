package com.red.common.code;

/**
 * Created by huichao on 2015/7/8.
 */
public class EntityCode {

    public static final int RED_STATUS_INVALIDE = 0;
    public static final int RED_STATUS_VALIDE = 1;


    public static final byte USER_VALIDATE=0;
    public static final byte USER_NO_VALIDATE=1;
    public static final byte USER_DELETE=2;

    public static final byte USER_FROM_MOBILE=0;
    public static final byte USER_FROM_QQ=1;
    public static final byte USER_FROM_WEIXIN=2;
    public static final byte USER_FROM_WEIBO=3;

    public static final byte USER_SOURCE_APP=0;
    public static final byte USER_SOURCE_SYS=1;

    public static final Integer MOBILE_REGESITER=1;
    public static final Integer MOBILE_GET_PASSWORD=2;
    public static final Integer MOBILE_BIND_UPDATE=3;
    public static final Integer MOBILE_YU_YUE=4;

    public static final Integer EMAIL_PWD=1;
    public static final Integer EMAIL_ACTIVE=2;

    public static final String BILLTYPE_CODE_CASH="cash";
    public static final String BILLTYPE_CODE_GIFT="gift";

    public static final Integer USER_HISTORY_MY=1;
    public static final Integer USER_HISTORY_FAIR=2;

    public static final Integer USER_RED_HAS_RECEIVE=1;
    public static final Integer USER_RED_HASNO_RECEIVE=2;
}
