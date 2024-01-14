package com.example.rmsoft.domain.solution.service;

import com.example.rmsoft.domain.solution.entity.Solution;
import com.example.rmsoft.domain.solution.repository.SolutionRepository;
import com.example.rmsoft.global.exception.BusinessLogicException;
import com.example.rmsoft.global.exception.ExceptionCode;
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

        return solutionRepository.findById(solutionId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.SOLUTION_NOT_FOUND));
    }

    public Solution findSolution(Long solutionId, OperatingSystem os) {

        Solution foundSolution = findSolution(solutionId);

        checkValidOS(foundSolution, os);

        return foundSolution;
    }

    private void checkValidOS(Solution solution, OperatingSystem os) {

        if (!solution.getOs().contains(os)) throw new BusinessLogicException(ExceptionCode.NOT_SUPPORTED_OS);
    }
}
