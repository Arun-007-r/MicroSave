package com.example.microsave.repository;

import com.example.microsave.entity.Contribution;
import com.example.microsave.entity.Member;
import com.example.microsave.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ContributionRepository
        extends JpaRepository<Contribution, Long> {

    List<Contribution> findByMember(Member member);

    @Query("""
        SELECT COALESCE(SUM(c.amount), 0)
        FROM Contribution c
        WHERE c.member.group = :group
    """)
    BigDecimal getTotalContributionsByGroup(
            @Param("group") Group group);

    @Query("""
        SELECT COALESCE(SUM(c.amount), 0)
        FROM Contribution c
        WHERE c.member = :member
    """)
    BigDecimal getTotalContributionsByMember(
            @Param("member") Member member);
}