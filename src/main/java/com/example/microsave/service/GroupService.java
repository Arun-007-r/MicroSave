package com.example.microsave.service;

import com.example.microsave.dto.GroupSummary;
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
public class GroupService {

    private final GroupRepository groupRepository;
    private final ContributionRepository contributionRepository;
    private final LoanRepository loanRepository;
    private final MemberRepository memberRepository;

    public GroupService(
            GroupRepository groupRepository,
            ContributionRepository contributionRepository,
            LoanRepository loanRepository,
            MemberRepository memberRepository) {

        this.groupRepository = groupRepository;
        this.contributionRepository = contributionRepository;
        this.loanRepository = loanRepository;
        this.memberRepository = memberRepository;
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }

    public GroupSummary getGroupSummary(Long groupId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        BigDecimal totalSavings =
                contributionRepository.getTotalContributionsByGroup(group);

        BigDecimal outstandingLoans =
                loanRepository.getOutstandingLoansByGroup(group);

        return new GroupSummary(
                group.getId(),
                group.getName(),
                totalSavings,
                outstandingLoans);
    }

    public List<Member> getGroupMembers(Long groupId) {

        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        return memberRepository.findByGroup(group);
    }
}