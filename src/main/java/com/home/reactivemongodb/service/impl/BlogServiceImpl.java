package com.home.reactivemongodb.service.impl;

import com.home.reactivemongodb.model.impl.Blog;
import com.home.reactivemongodb.repository.BlogRepository;
import com.home.reactivemongodb.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by marcin.bracisiewicz
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Mono<Blog> createBlog(Blog blog) {
        return null;
    }

    @Override
    public Flux<Blog> findByTitle(String title) {
        return null;
    }
}
