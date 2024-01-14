package com.example.rmsoft.domain.subscription.mapper;

import com.example.rmsoft.domain.solution.mapper.SolutionMapper;
import com.example.rmsoft.domain.subscription.dto.SubscriptionPostDto;
import com.example.rmsoft.domain.subscription.dto.SubscriptionResponseDto;
import com.example.rmsoft.domain.subscription.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = SolutionMapper.class)
public interface SubscriptionMapper {

    Subscription subscriptionDtoToSubscription(SubscriptionPostDto subscriptionPostDto);

    @Mapping(source = "solution", target = "solution", qualifiedByName = "SolutionToSolutionDto")
    SubscriptionResponseDto subscriptionToSubscriptionDto(Subscription subscription);

    default List<SubscriptionResponseDto> mapToSubscriptionDtoList(List<Subscription> subscriptions) {

        return subscriptions.stream()
                .map(subscription -> subscriptionToSubscriptionDto(subscription))
                .collect(Collectors.toList());
    }
}
