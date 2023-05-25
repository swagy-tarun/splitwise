package com.demo.splitwise.controller;

import com.demo.splitwise.business.service.ExpenseService;
import com.demo.splitwise.common.exceptions.UserNotFoundException;
import com.demo.splitwise.controller.requests.ExpenseRequest;
import com.demo.splitwise.controller.response.GenericResponse;
import com.demo.splitwise.infrastructure.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/expense")
    public ResponseEntity<GenericResponse<Expense>> addExpense(@RequestBody ExpenseRequest expense) {

        GenericResponse<Expense> genericResponse = new GenericResponse<>();
        Expense result = null;
        try {
            result = expenseService.createExpense(expense);
        } catch (UserNotFoundException e) {
            genericResponse.setError(e.getMessage());
            return ResponseEntity.badRequest().body(genericResponse);
        }

        genericResponse.setResult(result);
        return ResponseEntity.ok(genericResponse);
    }
}
