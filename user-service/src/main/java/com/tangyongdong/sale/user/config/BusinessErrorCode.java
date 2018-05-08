package com.tangyongdong.sale.user.config;


import com.tangyongdong.sale.base.exception.ErrorCode;

/**
 * @author tangyongdong
 * @create 2018-05-02 16:56
 */
public enum BusinessErrorCode implements ErrorCode {

    SYSTEM_BUSY(1,"A0001","系统繁忙请稍后再试"),
    MD5_SIGN_ERROR(2,"A0002","MD5加密失败"),
    MD5_VERIFY_ERROR(2,"A0003","MD5验签失败"),
    PARAMS_ERROR(2,"B0001","参数异常"),
    USER_NOT_FOUND(2,"C0001","未查询到用户信息"),
    USER_LOGIN_ERROR(2,"C0002","登录失败"),
    USER_NO_LOGIN(1,"C0003","用户未登录");

    private Integer errorStatus;
    private String errorCode;
    private String errorMessage;

    BusinessErrorCode(Integer errorStatus, String errorCode, String errorMessage) {
        this.errorStatus = errorStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public Integer getStatus() {
        return this.errorStatus;
    }

    @Override
    public String getCode() {
        return this.errorCode;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
