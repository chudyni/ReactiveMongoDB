package com.home.reactivemongodb.service;


import com.home.reactivemongodbapi.model.impl.Blog;

/**
 * Created by marcin.bracisiewicz
 */
@FunctionalInterface
public interface NotificationService {

    void send(Blog blog);
}
