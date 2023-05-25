package com.demo.splitwise.service.split;

import com.demo.splitwise.business.split.EqualSplitStrategy;
import com.demo.splitwise.common.enums.ShareMethod;
import com.demo.splitwise.infrastructure.model.Expense;
import com.demo.splitwise.infrastructure.model.Transaction;
import com.demo.splitwise.infrastructure.model.AppUser;
import com.demo.splitwise.business.vo.UserShare;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EqualSplitStrategyTest {

    Expense expense = null;
    List<UserShare> userShares = null;
    EqualSplitStrategy strategy = null;

    @BeforeEach
    public void before() {

        expense = new Expense();
        userShares = new ArrayList<>();
        strategy = new EqualSplitStrategy();

        AppUser user1 = new AppUser();
        user1.setId(1l);
        user1.setName("Tarun");
        AppUser user2 = new AppUser();
        user2.setId(2l);
        user2.setName("Divya");
        AppUser user3 = new AppUser();
        user3.setId(3l);
        user3.setName("Sanjay");
        AppUser user4 = new AppUser();
        user4.setId(4l);
        user4.setName("Firoz");

        expense.setAmount(new BigDecimal(1000));
        expense.setId(UUID.randomUUID());
        expense.setDescription("Expense for Lift AMC");
        expense.setUpdatedAt(LocalDateTime.now());
        expense.setOwner(user1);
        expense.setTitle("Lift AMC");

        userShares.add(new UserShare(UUID.randomUUID(), user1, null));
        userShares.add(new UserShare(UUID.randomUUID(), user2, null));
        userShares.add(new UserShare(UUID.randomUUID(), user3, null));
        userShares.add(new UserShare(UUID.randomUUID(), user4, null));

    }

    @AfterEach
    public void after() {
        expense = null;
        userShares = null;
        strategy = null;
    }

    @Test
    void testEqualSplit() {

        Map<String, Transaction> result = strategy.split(expense, userShares);
        assertEquals(4, result.size());
        userShares.stream().forEach(it -> {
            Transaction t = result.get(it.getId().toString());
            assertNotNull(t);
            assertEquals(t.getLentBy(), expense.getOwner());
            assertEquals(t.getShareAmount(), new BigDecimal(250));
            assertEquals(t.getShare(), new BigDecimal(25));
            assertEquals(t.getShareMethod(), ShareMethod.EQUAL);
            assertEquals(t.getBorrowedBy(), it.getUser());
        });

    }
}