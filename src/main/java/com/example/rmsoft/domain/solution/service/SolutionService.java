package com.example.rmsoft.domain.solution.service;

import com.example.rmsoft.domain.solution.entity.Solution;
import com.example.rmsoft.domain.solution.repository.SolutionRepository;
import com.example.rmsoft.global.value.OperatingSystem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SolutionService {

    private final SolutionRepository solutionRepository;

    public Solution createSolution(Solution solution) {

        return solutionRepository.save(solution);
    }

    public Solution findSolution(Long solutionId) {

        return solutionRepository.findById(solutionId).orElseThrow(() -> new RuntimeException("Solution Not Found"));
    }

    public Solution findSolution(Long solutionId, OperatingSystem os) {

        Solution foundSolution = findSolution(solutionId);

        if (!foundSolution.getOs().contains(os)) throw new RuntimeException("Not Supported OS");

        return foundSolution;
    }
}
