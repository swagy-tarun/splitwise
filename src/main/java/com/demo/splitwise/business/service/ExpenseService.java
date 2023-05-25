package com.demo.splitwise.business.service;

import com.demo.splitwise.business.split.SplitServiceLocator;
import com.demo.splitwise.business.split.SplitStrategy;
import com.demo.splitwise.business.vo.UserShare;
import com.demo.splitwise.common.exceptions.UserNotFoundException;
import com.demo.splitwise.infrastructure.model.AppUser;
import com.demo.splitwise.infrastructure.model.Expense;
import com.demo.splitwise.controller.requests.ExpenseRequest;
import com.demo.splitwise.infrastructure.model.Transaction;
import com.demo.splitwise.infrastructure.repository.ExpenseRepository;
import com.demo.splitwise.infrastructure.repository.TransactionRepository;
import com.demo.splitwise.infrastructure.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Optional;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private SplitServiceLocator splitServiceLocator;

    @Transactional
    public Expense createExpense(final ExpenseRequest expenseRequest) {
        final Expense expense = new Expense();

        Optional<AppUser> owner = userRepository.findById(expenseRequest.getAddedBy());

        if (owner.isPresent()) {
            expense.setOwner(owner.get());
        } else {
            String format = "Expense owner not found %d".formatted(expenseRequest.getAddedBy());
            throw new UserNotFoundException(format);
        }

        expense.setId(UUID.randomUUID());
        expense.setDescription(expenseRequest.getDescription());
        expense.setTitle(expenseRequest.getTitle());
        expense.setAmount(expenseRequest.getAmount());
        expense.setUpdatedAt(LocalDateTime.now());

        expenseRepository.save(expense);

        final SplitStrategy<Map<String, Transaction>, List<UserShare>> splitStrategy
                = splitServiceLocator.getStrategy(expenseRequest.getShareMethod());
        final List<UserShare> userShares = new ArrayList<>();

        //  TODO Error handling for user not found
        expenseRequest.getUserShares().forEach(shareInfo -> {
            UserShare userShare = new UserShare(UUID.randomUUID(),
                    userRepository.findById(shareInfo.getUserId()).get(), shareInfo.getShare());
            userShares.add(userShare);
        });

        final Map<String, Transaction> transactions = splitStrategy.split(expense, userShares);

        // TODO DTO required to prepare the clean response for this method
        expense.setTransactions(transactionRepository.saveAll(transactions.values()));
        return expense;
    }
}
