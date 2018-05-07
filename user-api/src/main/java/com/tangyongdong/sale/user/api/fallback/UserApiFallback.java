package com.tangyongdong.sale.user.api.fallback;

import com.tangyongdong.sale.base.exception.BusinessException;
import com.tangyongdong.sale.base.response.CommonResponse;
import com.tangyongdong.sale.user.api.UserApi;
import com.tangyongdong.sale.user.config.BusinessErrorCode;
import com.tangyongdong.sale.user.response.UserResponse;
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
        };
    }
}
