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

    @PostMapping("/group/{groupId}")
    public Member addMember(
            @PathVariable Long groupId,
            @RequestBody Member member) {

        return memberService.addMember(groupId, member);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/group/{groupId}")
    public List<Member> getMembersByGroup(
            @PathVariable Long groupId) {

        return memberService.getMembersByGroup(groupId);
    }

    @GetMapping("/{id}")
    public Member getMemberById(
            @PathVariable Long id) {

        return memberService.getMemberById(id);
    }

    @GetMapping("/{id}/summary")
    public MemberSummary getMemberSummary(
            @PathVariable Long id) {

        return memberService.getMemberSummary(id);
    }
}