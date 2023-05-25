package com.demo.splitwise.controller.requests;

import com.demo.splitwise.common.enums.ShareMethod;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ExpenseRequest {

    private Long addedBy;
    private BigDecimal amount;
    private List<ShareInfo> userShares;
    private ShareMethod shareMethod;
    private String title;
    private String description;

    @Data
    public static class ShareInfo {
        Long userId;
        BigDecimal share;
    }
}


