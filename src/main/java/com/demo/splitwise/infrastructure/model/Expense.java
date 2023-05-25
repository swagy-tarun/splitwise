package com.demo.splitwise.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(of = {"id"})
public class Expense {
    private @Id
    UUID id;
    private String title;
    private String description;
    private BigDecimal amount;
    private LocalDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "owner")
    private AppUser owner;

    @Transient
    private List<Transaction> transactions;
}
