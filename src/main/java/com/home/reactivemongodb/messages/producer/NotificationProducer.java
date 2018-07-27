package com.home.reactivemongodb.messages.producer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author marcin.bracisiewicz
 */
@Component
public class NotificationProducer {

  @Autowired
  private RabbitTemplate template;

  @Autowired
  @Qualifier(value = "standardNotificationQueue")
  private Queue standardNotificationQueue;

  public void send(final String message) {
    this.template.convertAndSend(this.standardNotificationQueue.getName(), message);
  }
}
