package com.assignment.expensetracker.validations;

import com.assignment.expensetracker.exceptions.ValidationException;

import java.util.HashSet;
import java.util.Set;

public class SubCategoryRegistry {

    public static HashSet<String> expenseSet = new HashSet(Set.of("food","rent","travel"));
    public static HashSet<String> incomeSet = new HashSet(Set.of("salary","business"));

    public static void checkExpense(String str) throws ValidationException {
        if(!expenseSet.contains(str))
        {
            throw new ValidationException("For Expense type SubCategory must be Food,Rent and Travel");
        }
    }

    public static void checkIncome(String str) throws ValidationException {

        if(!incomeSet.contains(str))
        {
            throw new ValidationException("For Income type SubCategory must be Salary and Business");
        }
    }
}
