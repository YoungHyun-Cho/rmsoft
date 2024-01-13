package com.example.rmsoft.global.config;

import com.example.rmsoft.domain.solution.entity.Solution;
import com.example.rmsoft.domain.solution.service.SolutionService;
import com.example.rmsoft.global.value.OperatingSystem;
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

        solutions.add(new Solution(
                1L, "ArchiveSolution", "1.3", 10000,
                new ArrayList<>(Arrays.asList(OperatingSystem.WINDOWS, OperatingSystem.MAC, OperatingSystem.LINUX))
        ));
        solutions.add(new Solution(
                2L, "Cliveworks", "1.5", 20000,
                new ArrayList<>(Arrays.asList(OperatingSystem.WINDOWS, OperatingSystem.MAC))
        ));
        solutions.add(new Solution(
                3L, "Archivistore", "1.7", 30000,
                new ArrayList<>(Arrays.asList(OperatingSystem.WINDOWS, OperatingSystem.LINUX))
        ));

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
