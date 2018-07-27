package com.home.reactivemongodb.service.impl;

import com.home.reactivemongodb.messages.producer.NotificationProducer;
import com.home.reactivemongodb.model.impl.Blog;
import com.home.reactivemongodb.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcin.bracisiewicz
 */
@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationProducer notificationProducer;

    @Override
    public void send(Blog blog) {
        this.notificationProducer.send(blog.toString());
    }
}
