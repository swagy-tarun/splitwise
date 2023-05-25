package com.demo.splitwise.infrastructure.model;

import com.demo.splitwise.common.enums.ShareMethod;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(of = {"id"})
public class Transaction {

    private @Id
    UUID id;
    @ManyToOne
    @JoinColumn(name = "EXPENSE_ID")
    private Expense expense;
    @ManyToOne
    @JoinColumn(name = "LENT_BY")
    private AppUser lentBy;
    @ManyToOne
    @JoinColumn(name = "BORROWED_BY")
    private AppUser borrowedBy;
    private BigDecimal shareAmount;
    private ShareMethod shareMethod;
    private BigDecimal share;
}
