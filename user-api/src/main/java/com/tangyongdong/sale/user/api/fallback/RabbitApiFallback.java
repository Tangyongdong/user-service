package com.tangyongdong.sale.user.api.fallback;

import com.tangyongdong.sale.base.exception.BusinessException;
import com.tangyongdong.sale.base.response.CommonResponse;
import com.tangyongdong.sale.user.api.RabbitApi;
import com.tangyongdong.sale.user.config.BusinessErrorCode;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tangyongdong
 * @create 2018-05-10 10:22
 */
@Slf4j
public class RabbitApiFallback implements FallbackFactory {
    @Override
    public Object create(Throwable throwable) {
        log.error("RabbitApi fallback:{}", throwable);
        return new RabbitApi() {

            @Override
            public CommonResponse<Boolean> helloWorld() {
                throw new BusinessException(BusinessErrorCode.SYSTEM_BUSY);
            }

            @Override
            public CommonResponse<Boolean> workQueue() {
                throw new BusinessException(BusinessErrorCode.SYSTEM_BUSY);
            }

            @Override
            public CommonResponse<Boolean> entityTransport() {
                throw new BusinessException(BusinessErrorCode.SYSTEM_BUSY);
            }

            @Override
            public CommonResponse<Boolean> fanoutExchange() {
                throw new BusinessException(BusinessErrorCode.SYSTEM_BUSY);
            }

            @Override
            public CommonResponse<Boolean> topicExchange() {
                throw new BusinessException(BusinessErrorCode.SYSTEM_BUSY);
            }
        };
    }
}
