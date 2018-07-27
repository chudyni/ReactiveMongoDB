package com.home.reactivemongodb;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * From:
 * https://medium.com/@beladiyahardik7/spring-boot-2-mongodb-reactive-programming-b20a9a5bd6c
 */
@EnableMongoAuditing
@EnableReactiveMongoRepositories
@SpringBootApplication
public class ReactivemongodbApplication {

    private static final String RANDOM_EXCHANGE = "randomExchange";

    private static final String NOTIFICATION_QUEUE_NAME = "notificationQueue";
    private static final String RANDOM_QUEUE_NAME = "randomQueue";

    public static final String RANDOM_KEY = "random";

    public static void main(String[] args) {
        SpringApplication.run(ReactivemongodbApplication.class, args);
    }

    @Bean
    public DirectExchange randomExchange() {
        return new DirectExchange(RANDOM_EXCHANGE);
    }

    /*
        Queue for DEFAULT exchange
     */
    @Bean
    public Queue standardNotificationQueue() {
        return new Queue(NOTIFICATION_QUEUE_NAME);
    }

    @Bean
    public Queue randomQueue() {
        return new Queue(RANDOM_QUEUE_NAME);
    }

    /*
        Bindings
     */
    @Bean
    public Binding randomQueueBinding(final DirectExchange randomExchange, final Queue randomQueue) {
        return BindingBuilder.bind(randomQueue)
            .to(randomExchange)
            .with(RANDOM_KEY);
    }
}


