package com.example.rmsoft.domain.solution.entity;

import com.example.rmsoft.global.audit.Auditable;
import com.example.rmsoft.global.value.OperatingSystem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Solution extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String version;

    @Column(nullable = false)
    private Integer pricePerDay;

    @Column(nullable = false, name = "operating_system")
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<OperatingSystem> os;
}