package com.tangyongdong.sale.user.controller;

import com.tangyongdong.sale.base.exception.BusinessException;
import com.tangyongdong.sale.base.response.CommonResponse;
import com.tangyongdong.sale.user.api.UserApi;
import com.tangyongdong.sale.user.config.BusinessErrorCode;
import com.tangyongdong.sale.user.response.UserResponse;
import com.tangyongdong.sale.user.service.UserService;
import com.tangyongdong.sale.user.util.JsoupUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * @author tangyongdong
 * @create 2018-05-04 18:44
 */
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @Override
    public CommonResponse<UserResponse> userLogin(String phone) {
        if (StringUtils.isEmpty(phone)) {
            throw new BusinessException(BusinessErrorCode.PARAMS_ERROR);
        } else {
            phone = JsoupUtil.clean(phone);
        }
        return CommonResponse.success(userService.userLogin(phone));
    }
}
