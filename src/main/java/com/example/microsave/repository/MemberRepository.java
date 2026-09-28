package com.example.microsave.repository;

import com.example.microsave.entity.Member;
import com.example.microsave.entity.Group;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository
        extends JpaRepository<Member, Long> {

    List<Member> findByGroup(Group group);
}