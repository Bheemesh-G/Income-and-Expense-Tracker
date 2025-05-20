package com.assignment.expensetracker.dto;

public interface MonthlyAmountSummary {
    Integer getYear();
    Integer getMonth();
    Integer getAmount();
    String getType();
}
