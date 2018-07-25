package com.home.reactivemongodb.service.impl;

import com.home.reactivemongodb.model.impl.Blog;
import com.home.reactivemongodb.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcin.bracisiewicz
 */
@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    private List<Blog> globalBlogList = new ArrayList<>();

    @Override
    public void send(Blog blog) {
        this.globalBlogList.add(blog);
        this.displayList();
    }

    private void displayList() {
        this.globalBlogList.stream()
                .forEach(System.out::println);
    }
}
