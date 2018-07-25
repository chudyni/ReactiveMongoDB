package com.home.reactivemongodb.service;

import com.home.reactivemongodb.model.impl.Blog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by marcin.bracisiewicz
 */
public interface BlogService {

    Mono<Blog> createBlog(final Blog blog);

    Flux<Blog> findByTitle(final String title);

    Flux<Blog> findAll();

    Mono<Blog> update(final Blog blog, final String id);

    Mono<Blog> findOne(final String id);
}
