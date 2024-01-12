package com.example.rmsoft.domain.solution.repository;

import com.example.rmsoft.domain.solution.entity.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolutionRepository extends JpaRepository<Solution, Long> {
}
