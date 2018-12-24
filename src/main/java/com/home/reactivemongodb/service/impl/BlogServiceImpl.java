package com.home.reactivemongodb.service.impl;

import com.home.reactivemongodb.repository.BlogRepository;
import com.home.reactivemongodb.service.BlogService;
import com.home.reactivemongodb.service.NotificationService;
import com.home.reactivemongodb.service.ViewService;
import com.home.reactivemongodbapi.model.impl.Blog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("notificationServiceImpl")
    private NotificationService notificationService;

    @Autowired
    @Qualifier("delayingNotificationServiceImpl")
    private NotificationService deadLetteringNotificationService;

    @Override
    public Mono<Blog> createBlog(Blog blog) {
        return this.blogRepository.insert(blog)
            .doOnSuccess(this.notificationService::send);
    }

    @Override
    public Flux<Blog> findByTitle(String title) {
        final Flux<Blog> blog = this.blogRepository.findByTitle(title);
        blog.subscribe(retrieved -> log.info("SERVICE - Found by title"));
        blog.subscribe(this.deadLetteringNotificationService::send);
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
                            .subscribe(this.viewService::display);
                });
    }

    @Override
    public Mono<Blog> findOne(String id) {
        return this.blogRepository.findByIdAndDeleteIsFalse(id)
                .switchIfEmpty(Mono.error(new Exception("No blog found with id: " + id)));
    }

}
