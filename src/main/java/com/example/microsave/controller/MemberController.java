package com.example.microsave.controller;

import com.example.microsave.dto.MemberSummary;
import com.example.microsave.entity.Member;
import com.example.microsave.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // Add a member to a group
    @PostMapping("/group/{groupId}")
    public Member addMember(
            @PathVariable Long groupId,
            @RequestBody Member member) {

        return memberService.addMember(groupId, member);
    }

    // Get all members
    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    // Get member by ID
    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    // Get member savings and outstanding loan
    @GetMapping("/{id}/summary")
    public MemberSummary getMemberSummary(@PathVariable Long id) {
        return memberService.getMemberSummary(id);
    }
}