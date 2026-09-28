package com.example.microsave.service;

import com.example.microsave.entity.Loan;
import com.example.microsave.entity.Repayment;
import com.example.microsave.repository.LoanRepository;
import com.example.microsave.repository.RepaymentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDate;

@Service
public class RepaymentService {

    private final LoanRepository loanRepository;
    private final RepaymentRepository repaymentRepository;

    public RepaymentService(
            LoanRepository loanRepository,
            RepaymentRepository repaymentRepository) {

        this.loanRepository = loanRepository;
        this.repaymentRepository = repaymentRepository;
    }

    public Repayment repayLoan(Long loanId, BigDecimal amount) {

        // 1. Find the loan
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() ->
                        new RuntimeException("Loan not found"));

        // 2. Check repayment amount
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException(
                    "Repayment amount must be greater than zero");
        }

        // 3. Check repayment does not exceed outstanding loan
        if (amount.compareTo(loan.getOutstandingAmount()) > 0) {
            throw new RuntimeException(
                    "Repayment amount cannot exceed outstanding loan");
        }

        // 4. Reduce outstanding loan
        BigDecimal remainingAmount =
                loan.getOutstandingAmount().subtract(amount);

        loan.setOutstandingAmount(remainingAmount);

        // 5. Save updated loan
        loanRepository.save(loan);

        // 6. Create repayment record
        Repayment repayment = new Repayment();

        repayment.setLoan(loan);
        repayment.setAmount(amount);
        repayment.setRepaymentDate(LocalDate.now());

        // 7. Save repayment
        return repaymentRepository.save(repayment);
    }
    public List<Repayment> getRepaymentsByLoan(Long loanId) {

    Loan loan = loanRepository.findById(loanId)
            .orElseThrow(() -> new RuntimeException("Loan not found"));

    return repaymentRepository.findByLoan(loan);
    }
}