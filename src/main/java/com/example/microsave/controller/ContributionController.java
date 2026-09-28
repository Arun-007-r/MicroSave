package com.example.microsave.controller;

import com.example.microsave.entity.Contribution;
import com.example.microsave.service.ContributionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contributions")
public class ContributionController {

    private final ContributionService contributionService;

    public ContributionController(ContributionService contributionService) {
        this.contributionService = contributionService;
    }

    // Add contribution for a member
    @PostMapping("/member/{memberId}")
    public Contribution addContribution(
            @PathVariable Long memberId,
            @RequestBody Contribution contribution) {

        return contributionService.addContribution(
                memberId,
                contribution);
    }
}