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
        consumerGroup = "inventory_consumer_group",
        selectorExpression = "order_purchased" // Subscribe to messages with this tag
)
public class InventoryConsumer implements RocketMQListener<OrderMessage> {

    @Override
    public void onMessage(OrderMessage orderMessage) {
        log.info("Inventory System: Received Order Message: {}", orderMessage);
        // Simulate inventory update logic
        try {
            // Deduct items from stock, update database, etc.
            log.info("Inventory System: Successfully updated inventory for Order ID: {} with amount: {}",
                    orderMessage.getOrderId(), orderMessage.getAmount());
            // If any error occurs, you can throw an exception to trigger a re-delivery attempt
            // throw new RuntimeException("Simulated inventory update failure");
        } catch (Exception e) {
            log.error("Inventory System: Failed to process order message for Order ID: {}", orderMessage.getOrderId(), e);
            // RocketMQ will handle retries if an exception is thrown
            throw e;
        }
    }
}