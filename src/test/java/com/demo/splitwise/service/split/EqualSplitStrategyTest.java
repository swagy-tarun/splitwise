package com.demo.splitwise.service.split;

import com.demo.splitwise.enums.ShareMethod;
import com.demo.splitwise.model.Expense;
import com.demo.splitwise.model.Transaction;
import com.demo.splitwise.model.User;
import com.demo.splitwise.service.vo.UserShare;
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

        User user1 = new User();
        user1.setId("1");
        user1.setName("Tarun");
        User user2 = new User();
        user2.setId("2");
        user2.setName("Divya");
        User user3 = new User();
        user3.setId("3");
        user3.setName("Sanjay");
        User user4 = new User();
        user4.setId("4");
        user4.setName("Firoz");

        expense.setAmount(new BigDecimal(1000));
        expense.setId("1");
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