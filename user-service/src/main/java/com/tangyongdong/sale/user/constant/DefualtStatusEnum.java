package com.tangyongdong.sale.user.constant;

/**
 * @author tangyongdong
 * @create 2018-05-03 18:39
 */
public enum DefualtStatusEnum {
    VALID(1), UNVALID(0);

    DefualtStatusEnum(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Integer status;
}
