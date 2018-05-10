package com.tangyongdong.sale.user.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tangyongdong
 * @create 2018-05-09 19:05
 */
@Configuration
public class RabbitConfig {

    public static final String RABBIT_HELLO = "rabbit_hello";

    public static final String RABBIT_WORK = "rabbit_work";

    public static final String RABBIT_ENTITY = "rabbit_entity";

    public static final String FANOUT_EXCHANGE = "fanout_exchange";
    public static final String RABBIT_FANOUT_A = "rabbit_fanout_a";
    public static final String RABBIT_FANOUT_B = "rabbit_fanout_b";
    public static final String RABBIT_FANOUT_C = "rabbit_fanout_c";

    public static final String TOPIC_EXCHANGE = "topic_exchange";
    public static final String RABBIT_RED_TOPIC_A = "red.topic.a";
    public static final String RABBIT_RED_TOPIC_B = "red.topic.b";
    public static final String RABBIT_BLUE_TOPIC_A = "blue.topic.a";

    @Bean
    public Queue helloQueue(){
        return new Queue(RABBIT_HELLO);
    }

    @Bean
    public Queue workQueue(){
        return new Queue(RABBIT_WORK);
    }

    @Bean
    public Queue entityQueue(){
        return new Queue(RABBIT_ENTITY);
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }
    @Bean
    public Queue fanoutAQueue(){
        return new Queue(RABBIT_FANOUT_A);
    }
    @Bean
    public Queue fanoutBQueue(){
        return new Queue(RABBIT_FANOUT_B);
    }
    @Bean
    public Queue fanoutCQueue(){
        return new Queue(RABBIT_FANOUT_C);
    }
    @Bean
    Binding bindingExchangeA(Queue fanoutAQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutAQueue).to(fanoutExchange);
    }
    @Bean
    Binding bindingExchangeB(Queue fanoutBQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutBQueue).to(fanoutExchange);
    }
    @Bean
    Binding bindingExchangeC(Queue fanoutCQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutCQueue).to(fanoutExchange);
    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }
    @Bean
    public Queue redAQueue(){
        return new Queue(RABBIT_RED_TOPIC_A);
    }
    @Bean
    public Queue redBQueue(){
        return new Queue(RABBIT_RED_TOPIC_B);
    }
    @Bean
    public Queue blueAQueue(){
        return new Queue(RABBIT_BLUE_TOPIC_A);
    }
    @Bean
    Binding bindingExchangeRedTopicA(Queue redAQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(redAQueue).to(topicExchange).with("red.topic.a");
    }
    @Bean
    Binding bindingExchangeTopic(Queue redBQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(redBQueue).to(topicExchange).with("*.topic.*");
    }
    @Bean
    Binding bindingExchangeBlue(Queue blueAQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(blueAQueue).to(topicExchange).with("blue.#");
    }

}
