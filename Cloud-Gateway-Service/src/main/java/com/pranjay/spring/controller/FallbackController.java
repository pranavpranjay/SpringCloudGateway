package com.pranjay.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/orderFallback")
    public Mono<String> orderServiceFallback(){
        return Mono.just("Order servcie is taking too long to respond or api is down");
    }

    @RequestMapping("/paymentFallback")
    public Mono<String> paymentServiceFallback(){
        return Mono.just("Payment servcie is taking too long to respond or api is down");
    }

}
