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
        return this.blogRepository.insert(blog);
    }

    @Override
    public Flux<Blog> findByTitle(String title) {
        return this.blogRepository.findByTitle(title);
    }

    @Override
    public Flux<Blog> findAll() {
        return this.blogRepository.findAll();
    }

    @Override
    public Mono<Blog> update(Blog blog, String id) {
        return this.findOne(id)
                .doOnSuccess(result -> {
                    result.setAuthor(blog.getAuthor());
                    result.setTitle(blog.getTitle());
                    result.setContent(blog.getContent());

                    this.blogRepository.save(result)
                            .subscribe();
                });
    }

    @Override
    public Mono<Blog> findOne(String id) {
        return this.blogRepository.findByIdAndDeleteIsFalse(id)
                .switchIfEmpty(Mono.error(new Exception("No blog found with id: " + id)));
    }

}
