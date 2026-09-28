package com.example.microsave.service;

import com.example.microsave.entity.Group;
import com.example.microsave.repository.GroupRepository;
import com.example.microsave.entity.Loan;
import com.example.microsave.entity.Member;
import com.example.microsave.repository.ContributionRepository;
import com.example.microsave.repository.LoanRepository;
import com.example.microsave.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;
    private final ContributionRepository contributionRepository;
    private final GroupRepository groupRepository;

    public LoanService(
        LoanRepository loanRepository,
        MemberRepository memberRepository,
        ContributionRepository contributionRepository,
        GroupRepository groupRepository) {

    this.loanRepository = loanRepository;
    this.memberRepository = memberRepository;
    this.contributionRepository = contributionRepository;
    this.groupRepository = groupRepository;
}

    public Loan requestLoan(Long memberId, BigDecimal amount) {

        // 1. Find the member
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->
                        new RuntimeException("Member not found"));

        // 2. Validate loan amount
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException(
                    "Loan amount must be greater than zero");
        }

        // 3. Check whether member already has an unpaid loan
        List<Loan> activeLoans =
                loanRepository
                        .findByMemberAndOutstandingAmountGreaterThan(
                                member,
                                BigDecimal.ZERO);

        if (!activeLoans.isEmpty()) {
            throw new RuntimeException(
                    "Member already has an unpaid loan");
        }

        // 4. Get the member's group
        Group group = member.getGroup();

        if (group == null) {
            throw new RuntimeException(
                    "Member does not belong to a group");
        }

        // 5. Calculate total group contributions
        BigDecimal totalContributions =
                contributionRepository
                        .getTotalContributionsByGroup(group);

        // 6. Calculate total outstanding loans
        BigDecimal outstandingLoans =
                loanRepository
                        .getOutstandingLoansByGroup(group);

        // 7. Calculate available group pool
        BigDecimal availablePool =
                totalContributions.subtract(outstandingLoans);

        // 8. Check whether requested loan exceeds available pool
        if (amount.compareTo(availablePool) > 0) {
            throw new RuntimeException(
                    "Loan amount exceeds available group pool");
        }

        // 9. Create loan
        Loan loan = new Loan();

        loan.setMember(member);
        loan.setGroup(group);
        loan.setAmount(amount);
        loan.setOutstandingAmount(amount);
        loan.setLoanDate(LocalDate.now());

        // 10. Save loan
        return loanRepository.save(loan);
    }
    public List<Loan> getLoansByGroup(Long groupId) {

    Group group = groupRepository.findById(groupId)
            .orElseThrow(() -> new RuntimeException("Group not found"));

    return loanRepository.findByGroup(group);
}
}