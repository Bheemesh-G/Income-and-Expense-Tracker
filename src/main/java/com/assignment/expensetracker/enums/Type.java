package com.assignment.expensetracker.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Type {

    INCOME,
    EXPENSE;

    @JsonCreator
    public static Type fromValue(String value) {
        return Type.valueOf(value.toUpperCase());
    }

}
