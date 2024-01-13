package com.example.rmsoft.domain.solution.mapper;

import com.example.rmsoft.domain.solution.dto.SolutionDto;
import com.example.rmsoft.domain.solution.entity.Solution;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Named("SolutionMapper")
@Mapper(componentModel = "spring")
public interface SolutionMapper {

    @Named("SolutionToSolutionDto")
    SolutionDto solutionToSolutionDto(Solution solution);
}
