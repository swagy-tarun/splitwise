package com.demo.splitwise.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Expense {
    private String id;
    private String title;
    private String description;
    private BigDecimal amount;
    private LocalDateTime updatedAt;
    private User owner;
}
