package com.assignment.expensetracker.repository;

import com.assignment.expensetracker.dto.MonthlyAmountSummary;
import com.assignment.expensetracker.models.ExpenseIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseIncomeRepository extends JpaRepository<ExpenseIncome,Long> {

    @Query(value = """
            SELECT
                YEAR(date) AS year,
                MONTH(date) AS month,
                SUM(amount) AS amount,
                type
            FROM
                expense_income
            GROUP BY
                YEAR(date), MONTH(date),type
            ORDER BY
                year, month
    """, nativeQuery = true)
    List<MonthlyAmountSummary> getMonthlyAmountSummaryByType();
}
