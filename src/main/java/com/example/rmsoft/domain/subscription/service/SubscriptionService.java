package com.example.rmsoft.domain.subscription.service;

import com.example.rmsoft.domain.subscription.entity.Subscription;
import com.example.rmsoft.domain.subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public Subscription createSubscription(Subscription subscription) {

        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> findAllSubscriptions(Long userId) {

        return subscriptionRepository.findByUserId(userId);
    }

    public Subscription updateExpiration(Long subscriptionId, LocalDateTime expiration) {

        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow(() -> new RuntimeException("Subscription Not Found"));

        subscription.setExpiration(expiration);

        return subscriptionRepository.save(subscription);
    }
}
