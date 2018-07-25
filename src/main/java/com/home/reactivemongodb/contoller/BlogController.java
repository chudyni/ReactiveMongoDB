package com.home.reactivemongodb.contoller;

import com.home.reactivemongodb.model.impl.Blog;
import com.home.reactivemongodb.service.BlogService;
import com.home.reactivemongodb.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by marcin.bracisiewicz
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/find")
    public Flux<Blog> findByTitle(@RequestParam final String title) {
        log.debug("findByTitle Blog with blogTitle : {}", title);
        final Flux<Blog> blog = this.blogService.findByTitle(title);
        blog.subscribe(retrieved -> log.info("CONTROLLER - Found by title"));
        return blog;
    }

    @GetMapping
    public Flux<Blog> findAll() {
        return this.blogService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Blog> create(@RequestBody final Blog blog) {
        log.debug("create Blog with blog : {}", blog);
        final Mono<Blog> created = this.blogService.createBlog(blog);
        created.subscribe(this.notificationService::send);
        return created;
    }

    @PutMapping("/{id}")
    public Mono<Blog> updateBlog(@RequestBody final Blog blog, @PathVariable final String id) {
        return this.blogService.update(blog, id);
    }
}
