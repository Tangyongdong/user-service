package com.tangyongdong.sale.user.api;

import com.tangyongdong.sale.base.response.CommonResponse;
import com.tangyongdong.sale.user.response.UserResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tangyongdong
 * @create 2018-05-04 18:40
 */
public interface UserApi {

    /**
     * 用户及管理员登录
     *
     * @param phone
     * @return
     */
    @RequestMapping(value = "/v1/all/login",method = RequestMethod.POST)
    CommonResponse<UserResponse> userLogin(@RequestParam("phone") String phone);
}
