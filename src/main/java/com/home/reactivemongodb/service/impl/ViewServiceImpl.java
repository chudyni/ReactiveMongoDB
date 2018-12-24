package com.home.reactivemongodb.service.impl;

import com.home.reactivemongodb.service.ViewService;
import com.home.reactivemongodbapi.model.impl.Blog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by marcin.bracisiewicz
 */
@Slf4j
@Service
public class ViewServiceImpl implements ViewService {

    @Override
    public void display(Blog blog) {
      log.info(String.format("Title: %s \n Author: %s \n Text: %s \n",
              blog.getTitle(),
              blog.getAuthor(),
              blog.getContent()));
    }
}
