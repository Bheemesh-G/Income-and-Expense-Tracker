package com.assignment.expensetracker.service;


import com.assignment.expensetracker.dto.MonthlyAmountSummary;
import com.assignment.expensetracker.enums.Type;
import com.assignment.expensetracker.models.ExpenseIncome;
import com.assignment.expensetracker.repository.ExpenseIncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseIncomeService {

    @Autowired
    private ExpenseIncomeRepository expenseIncomeRepository;

    public void addExpenseIncome(ExpenseIncome expenseIncome)
    {
        ExpenseIncome savedExpenseIncome = expenseIncomeRepository.save(expenseIncome);
    }

    public List<MonthlyAmountSummary> getSummary()
    {
        return  expenseIncomeRepository.getMonthlyAmountSummaryByType();
    }


    public void processFile(MultipartFile file) throws Exception {
        List<ExpenseIncome> entries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) { // Skip header
                    isFirstLine = false;
                    continue;
                }
                String[] data = line.split(",");

                if (data.length < 4) continue;

                ExpenseIncome entry = new ExpenseIncome();
                entry.setType(Type.valueOf(data[0].trim().toUpperCase()));
                entry.setSubCategory(data[1].trim());
                entry.setAmount(Double.parseDouble(data[2].trim()));
                entry.setDate(LocalDate.parse(data[3].trim()));

                entries.add(entry);
            }
        }
        expenseIncomeRepository.saveAll(entries);
    }



}
