package com.demo.splitwise.service.vo;

import com.demo.splitwise.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserShare {
    UUID id;
    User user;
    BigDecimal share;
}
