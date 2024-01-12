package com.example.rmsoft.domain.subscription.entity;

import com.example.rmsoft.domain.solution.entity.Solution;
import com.example.rmsoft.domain.user.entity.User;
import com.example.rmsoft.global.audit.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subscription extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "SOLUTION_ID")
    private Solution solution;

    @Column(nullable = false)
    private Integer userCount;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Integer storageUsage = 0;

    @Column(nullable = false)
    private Integer storageCapacity;

    @Column(nullable = false)
    private LocalDateTime expiration;
}