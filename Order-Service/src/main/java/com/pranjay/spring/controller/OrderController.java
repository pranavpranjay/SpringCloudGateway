package com.pranjay.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pranjay.spring.models.Payment;
import com.pranjay.spring.services.OrderService;
import com.pranjay.spring.models.Order;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
@RefreshScope
@Slf4j
//@Log4j2
public class OrderController {

    //Logger log = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private OrderService orderService;

    @Value("${microservice.order-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;

    @Autowired
    @Lazy
    RestTemplate restTemplate;

    @GetMapping("/v2/orders")
    public List <Order> getAllOrdersFromV2Api()
    {
        List<Order> empList = restTemplate.getForObject("http://second-service/api/v2/employees", List.class );
        return empList;
    }

    // get all employees
    @GetMapping("/orders")
    public List <Order> getAllOrders() {
        return orderService.findAll();
    }

    // create employee rest api
    @PostMapping("/orders")
    public Payment createOrder(@RequestBody Order order) throws JsonProcessingException {
        Order bookedOrder = orderService.save(order);
        log.info("Order Object: {}", new ObjectMapper().writeValueAsString(bookedOrder));
        Payment payment = new Payment();
        payment.setOrderId(bookedOrder.getId());
        payment.setAmount(bookedOrder.getQty());
        log.info("Order Object: {}", new ObjectMapper().writeValueAsString(payment));
        //return restTemplate.postForObject("http://payment-service/api/v2/payments", payment, Payment.class );
        //we can take end point for other api from config server which is act as a central repository
        return restTemplate.postForObject(ENDPOINT_URL, payment, Payment.class );
    }

    // get employee by id rest api
    @GetMapping("/orders/{id}")
    public ResponseEntity <Order> getOrderById(@PathVariable Long id) {
        Order employee = orderService.findById(id);
        return ResponseEntity.ok(employee);
    }

    // update employee rest api

    @PutMapping("/orders/{id}")
    public ResponseEntity <Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        Order employee = orderService.findById(id);

        employee.setName(orderDetails.getName());
        employee.setQty(orderDetails.getQty());
        employee.setPrice(orderDetails.getPrice());

        Order updatedEmployee = orderService.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    // delete employee rest api
    @DeleteMapping("/orders/{id}")
    public ResponseEntity < Map < String, Boolean >> deleteOrder(@PathVariable Long id) {
        Order employee = orderService.findById(id);

        orderService.delete(employee);
        Map< String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}