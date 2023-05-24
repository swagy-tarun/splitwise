package com.demo.splitwise.model;

import com.demo.splitwise.enums.ShareMethod;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Transaction {

    private UUID id;
    private Expense expense;
    private User lentBy;
    private User borrowedBy;
    private BigDecimal shareAmount;
    private ShareMethod shareMethod;
    private BigDecimal share;
}
