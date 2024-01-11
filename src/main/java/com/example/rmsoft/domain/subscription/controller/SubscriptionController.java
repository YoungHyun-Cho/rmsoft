package com.example.rmsoft.domain.subscription.controller;

import com.example.rmsoft.domain.subscription.dto.SubscriptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @PostMapping
    private ResponseEntity postSubscription(@AuthenticationPrincipal UserDetails userDetails,
                                            @RequestBody SubscriptionDto subscriptionDto) {

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity getSubscriptionInfo(@AuthenticationPrincipal UserDetails userDetails) {

        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping
    private ResponseEntity patchExpiration() {

        return new ResponseEntity(HttpStatus.OK);
    }
}
