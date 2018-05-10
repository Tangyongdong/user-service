package com.tangyongdong.sale.user.api;

import com.tangyongdong.sale.base.response.CommonResponse;
import com.tangyongdong.sale.user.api.fallback.RabbitApiFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author tangyongdong
 * @create 2018-05-10 10:22
 */
@FeignClient(value = "user-service", fallbackFactory = RabbitApiFallback.class)
public interface RabbitApi {

    /**
     * 最简单的helloWorld生产和消费实现
     * 单生产者和单消费者
     *
     * @return
     */
    @PostMapping(value = "/v1/rabbit/hello")
    CommonResponse<Boolean> helloWorld();

    /**
     * 工作队列
     *
     * @return
     */
    @PostMapping(value = "/v1/rabbit/work")
    CommonResponse<Boolean> workQueue();

    /**
     * 实体类传输（实体类必须实现序列化）
     *
     * @return
     */
    @PostMapping(value = "/v1/rabbit/entity")
    CommonResponse<Boolean> entityTransport();

    /**
     * 发布/订阅，广播模式
     *
     * @return
     */
    @PostMapping(value = "/v1/rabbit/fanout")
    CommonResponse<Boolean> fanoutExchange();

    /**
     * 路由模式
     *
     * @return
     */
    @PostMapping(value = "/v1/rabbit/topic")
    CommonResponse<Boolean> topicExchange();

}
