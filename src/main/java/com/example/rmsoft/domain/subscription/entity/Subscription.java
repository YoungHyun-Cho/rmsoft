package com.example.rmsoft.domain.subscription.entity;

import com.example.rmsoft.domain.solution.entity.Solution;
import com.example.rmsoft.domain.user.entity.User;
import com.example.rmsoft.global.audit.Auditable;
import com.example.rmsoft.global.value.ServiceType;
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
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Column(nullable = false)
    private Integer storageUsage;

    @Column(nullable = false)
    private Integer storageCapacity;

    @Column(nullable = false)
    private LocalDateTime expiration;

    @Column(nullable = false)
    private Long totalPrice;
}