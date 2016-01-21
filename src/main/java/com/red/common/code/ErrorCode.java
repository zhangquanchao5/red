package com.red.common.code;

/**
 * Created by huichao on 2015/7/8.
 */
public class ErrorCode {

    /**
     * 处理成功
     */
    public static final int SUCCESS = 200;
    /**
     * 处理失败
     */
    public static final int ERROR = 0;
    /**
     * 参数不完整
     */
    public static final int PARAMETER_NOT_ENOUGH = 4000;
    /**
     * 无效红包
     */
    public static final int RED_INVALIDE = 4001;
    /**
     * 无效红包详情
     */
    public static final int RED_DETAIL_INVALIDE = 4002;

    /**
     * 过期红包
     */
    public static final int RED_EXPIRE_DATE = 4003;

    /**
     * 红包领取完毕
     */
    public static final int RED_OVER_DATE = 4004;

    /**
     * 存在此账号
     */
    public static final int USER_EXITS=2003;
    /**
     * 不存在此账号
     */
    public static final int USER_NOT_EXITS=2002;
    /**
     * 用户密码错误
     */
    public static final int USER_PWD_ERROR=2001;

    /**
     * 用户密码错误
     */
    public static final int USER_NO_AUTH_ERROR=2004;

    /**
     * 验证码错误
     */
    public static final int USER_CODE_ERROR=2005;

    /**
     * 余额不足
     */
    public static final int USER_MONEY_ERROR=3001;

    /**
     * 余额不足
     */
    public static final int USER_TRADE_ERROR=3002;

    /**
     * 重复充值
     */
    public static final int REPEAT_DEPOSIT_ERROR =3003;

    /**
     * 重复扣款
     */
    public static final int REPEAT_WITHDRAW_ERROR=3004;

    /**
     * token no validate
     */
    public static final int USER_TOKEN_NO_VAL=9999;

    /**
     * 缺少请求头
     */
    public static final int USER_NO_HEADER=9990;
}
