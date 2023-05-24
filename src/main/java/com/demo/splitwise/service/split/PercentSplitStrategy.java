package com.demo.splitwise.service.split;

import com.demo.splitwise.enums.ShareMethod;
import com.demo.splitwise.model.Expense;
import com.demo.splitwise.model.Transaction;
import com.demo.splitwise.service.vo.UserShare;

import java.math.BigDecimal;
import java.util.*;

public class PercentSplitStrategy implements SplitStrategy<Map<String, Transaction>, List<UserShare>> {

    @Override
    public Map<String, Transaction> split(final Expense expense, final List<UserShare> users) {

        final Map<String, Transaction> transactions = new HashMap<>(users.size());

        for (UserShare userShare : users) {
            Transaction transaction = new Transaction();
            transaction.setId(userShare.getId());
            transaction.setExpense(expense);
            transaction.setBorrowedBy(userShare.getUser());
            transaction.setLentBy(expense.getOwner());
            BigDecimal expenseShare = new BigDecimal(String.valueOf(expense.getAmount()
                    .multiply(userShare.getShare())));
            transaction.setShareAmount(expenseShare);
            transaction.setShare(userShare.getShare());
            transaction.setShareMethod(ShareMethod.PERCENTAGE);
            transactions.put(transaction.getId().toString(), transaction);
        }

        return transactions;
    }
}
