package com.tangyongdong.sale.user.service;

/**
 * @author tangyongdong
 * @create 2018-05-10 10:35
 */
public interface RabbitService {

    /**
     * 最简单的helloWorld生产和消费实现
     * 单生产者和单消费者
     *
     * @return
     */
    Boolean helloWorld();

    /**
     * 单生产者-多消费者
     *
     * @return
     */
    Boolean workQueue();

    /**
     * 实体类传输
     *
     * @return
     */
    Boolean entityTransport();

    /**
     * 发布/订阅，广播模式
     *
     * @return
     */
    Boolean fanoutExchange();

    /**
     * 路由模式
     *
     * @return
     */
    Boolean topicExchange();
}
