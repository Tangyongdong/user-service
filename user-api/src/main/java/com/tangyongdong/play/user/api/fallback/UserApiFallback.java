package com.tangyongdong.play.user.api.fallback;

import com.tangyongdong.base.common.exception.BusinessException;
import com.tangyongdong.base.common.response.CommonResponse;
import com.tangyongdong.play.user.api.UserApi;
import com.tangyongdong.play.user.config.BusinessErrorCode;
import com.tangyongdong.play.user.request.AccessTokenAuthRequest;
import com.tangyongdong.play.user.response.UserResponse;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tangyongdong
 * @create 2018-05-04 18:41
 */
@Slf4j
public class UserApiFallback implements FallbackFactory {

    @Override
    public Object create(Throwable throwable) {
        log.error("UserApi fallback:{}", throwable);
        return new UserApi() {
            @Override
            public CommonResponse<UserResponse> userLogin(String phone) {
                throw new BusinessException(BusinessErrorCode.SYSTEM_BUSY);
            }

            @Override
            public CommonResponse<Boolean> accessTokenAuth(AccessTokenAuthRequest request) {
                throw new BusinessException(BusinessErrorCode.SYSTEM_BUSY);
            }

        };
    }
}
