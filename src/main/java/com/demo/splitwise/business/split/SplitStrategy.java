package com.demo.splitwise.business.split;

import com.demo.splitwise.infrastructure.model.Expense;
import com.demo.splitwise.infrastructure.model.Transaction;
import com.demo.splitwise.business.vo.UserShare;

import java.util.List;
import java.util.Map;

public interface SplitStrategy<T extends Map<String, Transaction>, R extends List<UserShare>> {

    T split(Expense expense, R users);
}
