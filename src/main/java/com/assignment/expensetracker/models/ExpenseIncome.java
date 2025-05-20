package com.assignment.expensetracker.models;

import com.assignment.expensetracker.enums.Type;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class ExpenseIncome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Type is required and must be INCOME or EXPENSE")
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotBlank(message = "Subcategory is required")
    private String subCategory;

    private Double amount;


    private LocalDate date;

    public ExpenseIncome() {
    }

    public ExpenseIncome(Long id, Type type, String subCategory, Double amount, LocalDate date) {
        this.id = id;
        this.type = type;
        this.subCategory = subCategory;
        this.amount = amount;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
