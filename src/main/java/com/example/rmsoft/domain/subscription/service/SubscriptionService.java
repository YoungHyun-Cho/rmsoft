package com.example.rmsoft.domain.subscription.service;

import com.example.rmsoft.domain.solution.entity.Solution;
import com.example.rmsoft.domain.solution.service.SolutionService;
import com.example.rmsoft.domain.subscription.entity.Subscription;
import com.example.rmsoft.domain.subscription.repository.SubscriptionRepository;
import com.example.rmsoft.global.config.PriceConfig;
import com.example.rmsoft.global.exception.BusinessLogicException;
import com.example.rmsoft.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public Subscription createSubscription(Subscription subscription) {

        verifyExistSubscription(subscription);

        subscription.setTotalPrice(calculateTotalPrice(subscription));
        subscription.setStorageUsage(0);

        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> findAllSubscriptions(Long userId) {

        return subscriptionRepository.findByUserId(userId);
    }

    public Subscription updateExpiration(Long subscriptionId, LocalDateTime expiration) {

        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.SUBSCRIPTION_NOT_FOUND));

        subscription.setExpiration(expiration);
        subscription.setTotalPrice(calculateTotalPrice(subscription));

        return subscriptionRepository.save(subscription);
    }

    private Long calculateTotalPrice(Subscription subscription) {

        Integer pricePerDay = subscription.getSolution().getPricePerDay();

        Long period = ChronoUnit.DAYS.between(LocalDateTime.now().toLocalDate(), subscription.getExpiration().toLocalDate()) - 1;

        Double servicePrice =
                pricePerDay
                * PriceConfig.multiplierByType(subscription.getServiceType())
                * subscription.getUserCount();

        Integer storagePrice = PriceConfig.multiplierByStorage(subscription.getStorageCapacity());

        return (servicePrice.intValue() + storagePrice) * period;
    }

    private void verifyExistSubscription(Subscription subscription) {

        List<Subscription> foundSubscriptions = findAllSubscriptions(subscription.getUser().getId());

        foundSubscriptions.stream().forEach(foundSubscription -> {
            if (foundSubscription.getUser().getId() == subscription.getUser().getId()
                    && foundSubscription.getSolution().getId() == subscription.getSolution().getId()) {
                throw new BusinessLogicException(ExceptionCode.SUBSCRIPTION_EXISTS);
            }
        });
    }
}
