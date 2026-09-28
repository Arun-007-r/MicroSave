package com.example.microsave.repository;

import com.example.microsave.entity.Loan;
import com.example.microsave.entity.Member;
import com.example.microsave.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    // Check whether a member already has an unpaid loan
    List<Loan> findByMemberAndOutstandingAmountGreaterThan(
            Member member,
            BigDecimal amount);

    // Calculate total outstanding loans of the group
    @Query("""
        SELECT COALESCE(SUM(l.outstandingAmount), 0)
        FROM Loan l
        WHERE l.group = :group
        AND l.outstandingAmount > 0
    """)
    BigDecimal getOutstandingLoansByGroup(
            @Param("group") Group group);

    // Calculate total outstanding loan of a member
    @Query("""
        SELECT COALESCE(SUM(l.outstandingAmount), 0)
        FROM Loan l
        WHERE l.member = :member
        AND l.outstandingAmount > 0
    """)
    BigDecimal getOutstandingLoanByMember(
            @Param("member") Member member);

    // Get all loans belonging to a group
    List<Loan> findByGroup(Group group);
}