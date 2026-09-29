package com.example.microsave.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "members")
@Getter
@Setter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Member name is required")
    @Pattern(
        regexp = "^[A-Za-z]+$",
        message = "Member name must contain only letters"
    )
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;
}