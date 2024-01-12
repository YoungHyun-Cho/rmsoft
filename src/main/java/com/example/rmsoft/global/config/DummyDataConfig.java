package com.example.rmsoft.global.config;

import com.example.rmsoft.domain.solution.entity.Solution;
import com.example.rmsoft.domain.solution.service.SolutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DummyDataConfig {

    private final SolutionService solutionService;
    private Boolean initiated = false;

    private List<Solution> makeDummySolutionData() {

        List<Solution> solutions = new ArrayList<>();

        solutions.add(new Solution(1L, "ArchiveSolution", "1.3", new ArrayList<>(Arrays.asList("Windows", "Mac", "Linux"))));
        solutions.add(new Solution(2L, "Cliveworks", "1.5", new ArrayList<>(Arrays.asList("Windows", "Mac"))));
        solutions.add(new Solution(3L, "Archivistore", "1.7", new ArrayList<>(Arrays.asList("Windows", "Linux"))));

        return solutions;
    }

    public void initiate() {

        if (!initiated) {

            makeDummySolutionData().stream()
                    .forEach(solution -> solutionService.createSolution(solution));

            initiated = true;
        }
    }
}
