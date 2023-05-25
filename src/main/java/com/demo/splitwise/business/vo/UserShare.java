package com.demo.splitwise.business.vo;

import com.demo.splitwise.infrastructure.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class UserShare {
    UUID id;
    AppUser user;
    BigDecimal share;
}
