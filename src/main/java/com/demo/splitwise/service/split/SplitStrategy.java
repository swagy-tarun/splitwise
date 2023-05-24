package com.demo.splitwise.service.split;

import com.demo.splitwise.model.Expense;
import com.demo.splitwise.model.Transaction;
import com.demo.splitwise.model.User;
import com.demo.splitwise.service.vo.UserShare;

import java.util.List;
import java.util.Map;

public interface SplitStrategy<T extends Map<String, Transaction>, R extends List<UserShare>> {

    T split(Expense expense, R users);
}
