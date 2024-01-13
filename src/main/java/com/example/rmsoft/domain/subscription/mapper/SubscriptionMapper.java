package com.example.rmsoft.domain.subscription.mapper;

import com.example.rmsoft.domain.solution.dto.SolutionDto;
import com.example.rmsoft.domain.solution.entity.Solution;
import com.example.rmsoft.domain.solution.mapper.SolutionMapper;
import com.example.rmsoft.domain.subscription.dto.SubscriptionRequestDto;
import com.example.rmsoft.domain.subscription.dto.SubscriptionResponseDto;
import com.example.rmsoft.domain.subscription.entity.Subscription;
import com.example.rmsoft.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = SolutionMapper.class)
public interface SubscriptionMapper {

    Subscription subscriptionDtoToSubscription(SubscriptionRequestDto subscriptionRequestDto);

    @Mapping(source = "solution", target = "solution", qualifiedByName = "SolutionToSolutionDto")
    SubscriptionResponseDto subscriptionToSubscriptionDto(Subscription subscription);

    default List<SubscriptionResponseDto> mapToSubscriptionDtoList(List<Subscription> subscriptions) {

        return subscriptions.stream()
                .map(subscription -> subscriptionToSubscriptionDto(subscription))
                .collect(Collectors.toList());
    }
}
