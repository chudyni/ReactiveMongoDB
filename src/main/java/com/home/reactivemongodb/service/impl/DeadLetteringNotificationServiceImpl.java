package com.home.reactivemongodb.service.impl;

import com.home.reactivemongodb.ReactivemongodbApplication;
import com.home.reactivemongodb.model.impl.Blog;
import com.home.reactivemongodb.service.NotificationService;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author marcin.bracisiewicz
 */
@Service
public class DeadLetteringNotificationServiceImpl implements NotificationService {

  @Autowired
  private RabbitTemplate template;

  @Autowired
  @Qualifier(value = "randomExchange")
  private DirectExchange randomExchange;

  @Override
  public void send(final Blog blog) {
    this.template.convertAndSend(
        this.randomExchange.getName(),
        ReactivemongodbApplication.RANDOM_KEY,
        "RANDOM: " + blog.toString()
    );
  }

}
