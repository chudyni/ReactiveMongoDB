package com.home.reactivemongodb.service.impl;

import com.home.reactivemongodb.model.impl.Blog;
import com.home.reactivemongodb.repository.BlogRepository;
import com.home.reactivemongodb.service.BlogService;
import com.home.reactivemongodb.service.NotificationService;
import com.home.reactivemongodb.service.ViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by marcin.bracisiewicz
 */
@Slf4j
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ViewService viewService;

    @Override
    public Mono<Blog> createBlog(Blog blog) {
        return this.blogRepository.insert(blog);
    }

    @Override
    public Flux<Blog> findByTitle(String title) {
        final Flux<Blog> blog = this.blogRepository.findByTitle(title);
        blog.subscribe(retrieved -> log.info("SERVICE - Found by title"));
        return blog;
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
                            .subscribe(viewService::display);
                });
    }

    @Override
    public Mono<Blog> findOne(String id) {
        return this.blogRepository.findByIdAndDeleteIsFalse(id)
                .switchIfEmpty(Mono.error(new Exception("No blog found with id: " + id)));
    }

}
