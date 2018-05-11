package com.tangyongdong.play.user.api;

import com.tangyongdong.base.common.response.CommonResponse;
import com.tangyongdong.play.user.api.fallback.UserApiFallback;
import com.tangyongdong.play.user.request.AccessTokenAuthRequest;
import com.tangyongdong.play.user.response.UserResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author tangyongdong
 * @create 2018-05-04 18:40
 */
@FeignClient(value = "user-service", fallbackFactory = UserApiFallback.class)
public interface UserApi {

    /**
     * 用户及管理员登录
     *
     * @param phone
     * @return
     */
    @RequestMapping(value = "/v1/all/login",method = RequestMethod.POST)
    CommonResponse<UserResponse> userLogin(@RequestParam("phone") String phone);

    /**
     * 验证用户accessToken
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/v1/access/token/auth",method = RequestMethod.POST)
    CommonResponse<Boolean> accessTokenAuth(@RequestBody AccessTokenAuthRequest request);
}
