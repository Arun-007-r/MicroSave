package com.example.microsave.repository;

import com.example.microsave.entity.Loan;
import com.example.microsave.entity.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepaymentRepository
        extends JpaRepository<Repayment, Long> {

    List<Repayment> findByLoan(Loan loan);
}