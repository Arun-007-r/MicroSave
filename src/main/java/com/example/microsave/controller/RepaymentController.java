package com.example.microsave.controller;

import com.example.microsave.entity.Repayment;
import com.example.microsave.service.RepaymentService;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Validated
@RequestMapping("/repayments")
public class RepaymentController {

    private final RepaymentService repaymentService;

    public RepaymentController(RepaymentService repaymentService) {
        this.repaymentService = repaymentService;
    }

   @PostMapping("/loan/{loanId}")
    public Repayment repayLoan(
            @PathVariable Long loanId,
            @RequestParam
            @jakarta.validation.constraints.NotNull
            @jakarta.validation.constraints.Positive
            BigDecimal amount) {

        return repaymentService.repayLoan(loanId, amount);
    }

    @GetMapping("/loan/{loanId}")
    public List<Repayment> getRepaymentsByLoan(
            @PathVariable Long loanId) {

        return repaymentService.getRepaymentsByLoan(loanId);
    }
}