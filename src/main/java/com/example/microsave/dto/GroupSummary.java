package com.example.microsave.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class GroupSummary {

    private Long groupId;
    private String groupName;
    private BigDecimal totalSavings;
    private BigDecimal outstandingLoans;
}