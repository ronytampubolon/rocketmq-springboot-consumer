package com.tampubolon.RocketMqConsumer.consumer;

import com.tampubolon.RocketMqConsumer.dto.OrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQMessageListener(
        topic = "order_events_topic",
        consumerGroup = "monitoring_consumer_group",
        selectorExpression = "order_created",
        messageModel = MessageModel.CLUSTERING
)
public class MonitoringConsumer implements RocketMQListener<OrderMessage> {

    @Override
    public void onMessage(OrderMessage orderMessage) {
        log.info("Monitoring System: Received Order Message: {}", orderMessage);
        // Simulate sending to monitoring
        try {
            log.info("Monitoring System: user {} for Order ID: {}",
                    orderMessage.getUserId(), orderMessage.getOrderId());
        } catch (Exception e) {
            log.error("Monitoring System: Failed to send Monitoring for Order ID: {}", orderMessage.getOrderId(), e);
            throw e; // Re-throw to indicate processing failure
        }
    }
}