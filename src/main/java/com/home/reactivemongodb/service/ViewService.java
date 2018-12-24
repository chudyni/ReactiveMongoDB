package com.home.reactivemongodb.service;

import com.home.reactivemongodbapi.model.impl.Blog;
import reactor.core.publisher.Mono;

/**
 * Created by marcin.bracisiewicz
 */
@FunctionalInterface
public interface ViewService {

    void display(final Blog blog);
}
