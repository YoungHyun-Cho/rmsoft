package com.example.rmsoft.domain.subscription.controller;

import com.example.rmsoft.domain.solution.entity.Solution;
import com.example.rmsoft.domain.solution.service.SolutionService;
import com.example.rmsoft.domain.subscription.dto.SubscriptionPatchDto;
import com.example.rmsoft.domain.subscription.dto.SubscriptionPostDto;
import com.example.rmsoft.domain.subscription.dto.SubscriptionResponseDto;
import com.example.rmsoft.domain.subscription.entity.Subscription;
import com.example.rmsoft.domain.subscription.mapper.SubscriptionMapper;
import com.example.rmsoft.domain.subscription.service.SubscriptionService;
import com.example.rmsoft.domain.user.entity.User;
import com.example.rmsoft.domain.user.service.UserService;
import com.example.rmsoft.global.util.Utility;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;
    private final UserService userService;
    private final SolutionService solutionService;

    @PostMapping
    private ResponseEntity postSubscription(@Valid @RequestBody SubscriptionPostDto subscriptionPostDto) {

        Subscription subscription = subscriptionMapper.subscriptionDtoToSubscription(subscriptionPostDto);
        User user = userService.findUser(subscriptionPostDto.getUserId());
        Solution solution = solutionService.findSolution(subscriptionPostDto.getSolutionId(), subscriptionPostDto.getOs());

        subscription.setUser(user);
        subscription.setSolution(solution);

        Subscription createdSubscription = subscriptionService.createSubscription(subscription);

        URI uri = Utility.makeURI("/subscriptions/{subscription-id}", createdSubscription.getId());

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    private ResponseEntity getSubscriptionInfo(@AuthenticationPrincipal UserDetails userDetails) {

        Long userId = Utility.getUserId(userDetails);

        List<Subscription> subscriptions = subscriptionService.findAllSubscriptions(userId);

        List<SubscriptionResponseDto> subscriptionResponseDtos = subscriptionMapper.mapToSubscriptionDtoList(subscriptions);

        return new ResponseEntity(subscriptionResponseDtos, HttpStatus.OK);
    }

    @PatchMapping("/{subscription-id}")
    private ResponseEntity patchExpiration(@PathVariable("subscription-id") Long subscriptionId,
                                           @Valid @RequestBody SubscriptionPatchDto subscriptionPatchDto) {

        Subscription subscription = subscriptionService.updateExpiration(subscriptionId, subscriptionPatchDto.getExpiration());

        SubscriptionResponseDto subscriptionResponseDto = subscriptionMapper.subscriptionToSubscriptionDto(subscription);

        return new ResponseEntity(subscriptionResponseDto, HttpStatus.OK);
    }
}
