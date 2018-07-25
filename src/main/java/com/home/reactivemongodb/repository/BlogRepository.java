package com.home.reactivemongodb.repository;

import com.home.reactivemongodb.model.impl.Blog;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by marcin.bracisiewicz
 */
@Repository
public interface BlogRepository extends ReactiveMongoRepository<Blog, String> {

    Flux<Blog> findByTitle(final String title);

    Mono<Blog> findByIdAndDeleteIsFalse(String id);
}
