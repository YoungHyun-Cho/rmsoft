package com.example.rmsoft.domain.subscription.mapper;

import com.example.rmsoft.domain.solution.entity.Solution;
import com.example.rmsoft.domain.subscription.dto.SubscriptionDto;
import com.example.rmsoft.domain.subscription.entity.Subscription;
import com.example.rmsoft.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    Subscription subscriptionDtoToSubscription(SubscriptionDto subscriptionDto);

    @Mapping(source = "user", target = "userId", qualifiedByName = "userToUserId")
    @Mapping(source = "solution", target = "solutionId", qualifiedByName = "solutionToSolutionId")
    SubscriptionDto subscriptionToSubscriptionDto(Subscription subscription);

    default List<SubscriptionDto> mapToSubscriptionDtoList(List<Subscription> subscriptions) {

        return subscriptions.stream()
                .map(subscription -> subscriptionToSubscriptionDto(subscription))
                .collect(Collectors.toList());
    }

    @Named("userToUserId")
    default Long userToUserId(User user) {
        return user.getId();
    }

    @Named("solutionToSolutionId")
    default Long solutionToSolutionId(Solution solution) {
        return solution.getId();
    }
}
