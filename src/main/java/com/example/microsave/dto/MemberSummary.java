package com.example.microsave.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class MemberSummary {

    private Long memberId;
    private String memberName;
    private BigDecimal totalSavings;
    private BigDecimal outstandingLoan;
}