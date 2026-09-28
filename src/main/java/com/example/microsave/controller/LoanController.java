package com.example.microsave.controller;

import com.example.microsave.entity.Loan;
import com.example.microsave.service.LoanService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
@RestController
@Validated 
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

   @PostMapping("/member/{memberId}")
    public Loan requestLoan(
            @PathVariable Long memberId,
            @RequestParam @jakarta.validation.constraints.NotNull
            @jakarta.validation.constraints.Positive
            BigDecimal amount) {

        return loanService.requestLoan(memberId, amount);
    }
    @GetMapping("/group/{groupId}")
    public List<Loan> getLoansByGroup(@PathVariable Long groupId) {
        return loanService.getLoansByGroup(groupId);
    }
}