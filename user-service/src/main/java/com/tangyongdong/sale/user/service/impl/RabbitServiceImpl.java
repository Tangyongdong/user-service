package com.tangyongdong.sale.user.service.impl;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.tangyongdong.sale.user.config.RabbitConfig;
import com.tangyongdong.sale.user.domain.db.User;
import com.tangyongdong.sale.user.mapper.UserMapper;
import com.tangyongdong.sale.user.response.UserResponse;
import com.tangyongdong.sale.user.service.RabbitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tangyongdong
 * @create 2018-05-10 10:35
 */
@Service
@Slf4j
public class RabbitServiceImpl implements RabbitService{

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean helloWorld() {
        User user = userMapper.selectByPhone("15821117963");
        log.info("=============== rabbit helloWorld send start");
        rabbitTemplate.convertAndSend(RabbitConfig.RABBIT_HELLO,user.getName());
        log.info("=============== rabbit helloWorld send end");
        return Boolean.TRUE;
    }

    @Override
    public Boolean workQueue() {
        User user = userMapper.selectByPhone("15821117963");
        log.info("=============== rabbit workQueue send start");
        rabbitTemplate.convertAndSend(RabbitConfig.RABBIT_WORK,user.getName());
        log.info("=============== rabbit workQueue send end");
        return Boolean.TRUE;
    }

    @Override
    public Boolean entityTransport() {
        User user = userMapper.selectByPhone("15821117963");
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .token(user.getToken())
                .name(user.getName())
                .phone(user.getPhone())
                .gender(user.getGender())
                .build();
        log.info("=============== rabbit entityTransport send start");
        rabbitTemplate.convertAndSend(RabbitConfig.RABBIT_ENTITY,userResponse);
        log.info("=============== rabbit entityTransport send end");
        return Boolean.TRUE;
    }

    @Override
    public Boolean fanoutExchange() {
        User user = userMapper.selectByPhone("15821117963");
        log.info("=============== rabbit fanoutExchange send start");
        rabbitTemplate.convertAndSend(RabbitConfig.FANOUT_EXCHANGE,"",user.getName());
        log.info("=============== rabbit fanoutExchange send end");
        return Boolean.TRUE;
    }

    @Override
    public Boolean topicExchange(){
        User user = userMapper.selectByPhone("15821117963");
        log.info("=============== rabbit topicExchange send start");
        try {
            rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,RabbitConfig.RABBIT_RED_TOPIC_A,user.getName().concat("1"));
            Thread.sleep(200);
            rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,RabbitConfig.RABBIT_RED_TOPIC_B,user.getName().concat("2"));
            Thread.sleep(200);
            rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,RabbitConfig.RABBIT_BLUE_TOPIC_A,user.getName().concat("3"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("=============== rabbit topicExchange send end");
        return Boolean.TRUE;
    }
}
