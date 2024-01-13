package com.example.rmsoft.domain.subscription.service;

import com.example.rmsoft.domain.solution.entity.Solution;
import com.example.rmsoft.domain.solution.service.SolutionService;
import com.example.rmsoft.domain.subscription.entity.Subscription;
import com.example.rmsoft.domain.subscription.repository.SubscriptionRepository;
import com.example.rmsoft.global.config.PriceConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public Subscription createSubscription(Subscription subscription) {

        subscription.setTotalPrice(calculateTotalPrice(subscription));
        subscription.setStorageUsage(0);

        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> findAllSubscriptions(Long userId) {

        return subscriptionRepository.findByUserId(userId);
    }

    public Subscription updateExpiration(Long subscriptionId, LocalDateTime expiration) {

        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow(() -> new RuntimeException("Subscription Not Found"));

        subscription.setExpiration(expiration);
        subscription.setTotalPrice(calculateTotalPrice(subscription));

        return subscriptionRepository.save(subscription);
    }

    private Integer calculateTotalPrice(Subscription subscription) {

        Period period = Period.between(LocalDateTime.now().toLocalDate(), subscription.getExpiration().toLocalDate());

        Double servicePrice =
                subscription.getSolution().getPricePerMonth()
                * PriceConfig.multiplierByType(subscription.getServiceType())
                * subscription.getUserCount();

        Integer storagePrice = PriceConfig.multiplierByStorage(subscription.getStorageCapacity());

        return (servicePrice.intValue() + storagePrice) * period.getDays();
    }
}
