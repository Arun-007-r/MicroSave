package com.example.microsave.service;

import com.example.microsave.dto.MemberSummary;
import com.example.microsave.entity.Group;
import com.example.microsave.entity.Member;
import com.example.microsave.repository.ContributionRepository;
import com.example.microsave.repository.GroupRepository;
import com.example.microsave.repository.LoanRepository;
import com.example.microsave.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final GroupRepository groupRepository;
    private final ContributionRepository contributionRepository;
    private final LoanRepository loanRepository;

    public MemberService(
            MemberRepository memberRepository,
            GroupRepository groupRepository,
            ContributionRepository contributionRepository,
            LoanRepository loanRepository) {

        this.memberRepository = memberRepository;
        this.groupRepository = groupRepository;
        this.contributionRepository = contributionRepository;
        this.loanRepository = loanRepository;
    }

    public Member addMember(Long groupId, Member member) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() ->
                        new RuntimeException("Group not found"));

        member.setGroup(group);

        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public List<Member> getMembersByGroup(Long groupId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() ->
                        new RuntimeException("Group not found"));

        return memberRepository.findByGroup(group);
    }

    public Member getMemberById(Long id) {

        return memberRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Member not found"));
    }

    public MemberSummary getMemberSummary(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() ->
                        new RuntimeException("Member not found"));

        BigDecimal totalSavings =
                contributionRepository
                        .getTotalContributionsByMember(member);

        BigDecimal outstandingLoan =
                loanRepository
                        .getOutstandingLoanByMember(member);

        return new MemberSummary(
                member.getId(),
                member.getName(),
                totalSavings,
                outstandingLoan);
    }
}