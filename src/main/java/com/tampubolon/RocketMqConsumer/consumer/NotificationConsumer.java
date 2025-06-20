package com.tampubolon.RocketMqConsumer.consumer;

import com.tampubolon.RocketMqConsumer.dto.OrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQMessageListener(
        topic = "order_events_topic",
        consumerGroup = "notification_consumer_group",
        selectorExpression = "order_purchased"
)
public class NotificationConsumer implements RocketMQListener<OrderMessage> {

    @Override
    public void onMessage(OrderMessage orderMessage) {
        log.info("Notification System: Received Order Message: {}", orderMessage);
        // Simulate sending notifications
        try {
            log.info("Notification System: Sending email/SMS to user {} for Order ID: {}",
                    orderMessage.getUserId(), orderMessage.getOrderId());
        } catch (Exception e) {
            log.error("Notification System: Failed to send notification for Order ID: {}", orderMessage.getOrderId(), e);
            throw e; // Re-throw to indicate processing failure
        }
    }
}