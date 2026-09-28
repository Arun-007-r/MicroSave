package com.example.microsave.service;

import com.example.microsave.entity.Contribution;
import com.example.microsave.entity.Member;
import com.example.microsave.repository.ContributionRepository;
import com.example.microsave.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ContributionService {

    private final ContributionRepository contributionRepository;
    private final MemberRepository memberRepository;

    public ContributionService(
            ContributionRepository contributionRepository,
            MemberRepository memberRepository) {

        this.contributionRepository = contributionRepository;
        this.memberRepository = memberRepository;
    }

    public Contribution addContribution(
            Long memberId,
            Contribution contribution) {

        // 1. Find the member
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->
                        new RuntimeException("Member not found"));

        // 2. Check contribution amount
        if (contribution.getAmount() == null ||
                contribution.getAmount()
                        .compareTo(BigDecimal.ZERO) <= 0) {

            throw new RuntimeException(
                    "Contribution amount must be greater than zero");
        }

        // 3. Check member belongs to a group
        if (member.getGroup() == null) {
            throw new RuntimeException(
                    "Member does not belong to a group");
        }

        // 4. Associate contribution with member
        contribution.setMember(member);

        // 5. Set date automatically if not provided
        if (contribution.getContributionDate() == null) {
            contribution.setContributionDate(LocalDate.now());
        }

        // 6. Save contribution
        return contributionRepository.save(contribution);
    }
}