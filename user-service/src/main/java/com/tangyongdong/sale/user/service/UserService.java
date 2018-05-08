package com.tangyongdong.sale.user.service;

import com.tangyongdong.sale.user.response.UserResponse;

/**
 * @author tangyongdong
 * @create 2018-05-07 11:02
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param phone
     * @return
     */
    UserResponse userLogin(String phone);

    /**
     * 验证用户accessToken
     *
     * @param userToken
     * @param accessToekn
     * @return
     */
    Boolean accessTokenAuth(String userToken, String accessToekn);
}
