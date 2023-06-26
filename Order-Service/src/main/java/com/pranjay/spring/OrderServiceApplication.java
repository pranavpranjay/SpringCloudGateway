package com.pranjay.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	RestTemplate getrestTemplate(){
		return new RestTemplate();
	}

	/*@Bean
	public AlwaysSampler defaultSampler() {
		return new AlwaysSampler();
	}*/
}