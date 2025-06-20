package com.tampubolon.RocketMqConsumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMessage implements Serializable {
    private String orderId;
    private Long userId;
    private Double amount;
    private String status;
    private LocalDateTime timestamp;
    private String productDetails;
}
