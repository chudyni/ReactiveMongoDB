package com.home.reactivemongodb.service;

import com.home.reactivemongodb.model.impl.Blog;

/**
 * Created by marcin.bracisiewicz
 */
@FunctionalInterface
public interface NotificationService {

    void send(Blog blog);
}
