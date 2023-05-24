package com.demo.splitwise.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Expense {
    private String id;
    private String title;
    private String description;
    private BigDecimal amount;
    private LocalDateTime updatedAt;
    private User owner;
}
