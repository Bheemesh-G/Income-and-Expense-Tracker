package com.assignment.expensetracker.controller;


import com.assignment.expensetracker.dto.MonthlyAmountSummary;
import com.assignment.expensetracker.enums.Type;
import com.assignment.expensetracker.exceptions.ValidationException;
import com.assignment.expensetracker.models.ExpenseIncome;
import com.assignment.expensetracker.service.ExpenseIncomeService;
import com.assignment.expensetracker.validations.SubCategoryRegistry;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.PublicKey;
import java.util.List;

@RestController
public class ExpenseIncomeController {

    @Autowired
    private ExpenseIncomeService expenseIncomeService;


    @PostMapping("/api/addExpenseIncome")
    public ResponseEntity<String> addExpenseIncome(@RequestBody @Valid ExpenseIncome expenseIncome) throws ValidationException {
        if(expenseIncome.getType().equals(Type.INCOME))
        {
            SubCategoryRegistry.checkIncome(expenseIncome.getSubCategory());
        }
        else{
            SubCategoryRegistry.checkExpense(expenseIncome.getSubCategory());
        }
        expenseIncomeService.addExpenseIncome(expenseIncome);
        String message = "Successfully added "+expenseIncome.getType();
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }



    @GetMapping("/api/getSummary")
    public ResponseEntity<List<MonthlyAmountSummary>> getSummary()
    {
        List<MonthlyAmountSummary> list = expenseIncomeService.getSummary();
     return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PostMapping("/api/upload")
    public ResponseEntity<String> uploadTransactions(@RequestParam("file") MultipartFile file) {
        try {
            expenseIncomeService.processFile(file);
            return ResponseEntity.status(HttpStatus.CREATED).body("File uploaded and data processed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to process file: " + e.getMessage());
        }
    }





}
