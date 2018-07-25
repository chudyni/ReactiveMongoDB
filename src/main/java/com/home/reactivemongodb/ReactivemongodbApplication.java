package com.home.reactivemongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

    public static void main(String[] args) {
        SpringApplication.run(ReactivemongodbApplication.class, args);
    }
}
