package com.demo.splitwise.service.split;

import com.demo.splitwise.enums.ShareMethod;
import com.demo.splitwise.model.Expense;
import com.demo.splitwise.model.Transaction;
import com.demo.splitwise.service.vo.UserShare;

import java.math.BigDecimal;
import java.util.*;

public class EqualSplitStrategy implements SplitStrategy<Map<String, Transaction>, List<UserShare>> {

    @Override
    public Map<String, Transaction> split(final Expense expense, final List<UserShare> users) {

        final BigDecimal expenseShare = expense.getAmount().divide(new BigDecimal(users.size()));
        final BigDecimal share = new BigDecimal(100).divide(new BigDecimal(users.size()));
        final Map<String, Transaction> transactions = new HashMap<>();


        for (UserShare userShare : users) {
            Transaction transaction = new Transaction();
            transaction.setId(userShare.getId());
            transaction.setExpense(expense);
            transaction.setBorrowedBy(userShare.getUser());
            transaction.setLentBy(expense.getOwner());
            transaction.setShareAmount(expenseShare);
            transaction.setShare(share);
            transaction.setShareMethod(ShareMethod.EQUAL);

            transactions.put(transaction.getId().toString(), transaction);
        }

        return transactions;
    }
}
