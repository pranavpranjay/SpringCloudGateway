package com.pranjay.spring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pranjay.spring.exceptions.ResourceNotFoundException;
import com.pranjay.spring.models.Payment;
import com.pranjay.spring.repository.PaymentRepository;
import com.pranjay.spring.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v2")
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    // get all employees
    @GetMapping("/payments")
    public List <Payment> getAllPayments() {
        return paymentService.findAll();
    }

    // create employee rest api
    @PostMapping("/payments")
    public Payment createPayment(@RequestBody Payment employee) throws JsonProcessingException {
        Payment payment = paymentService.save(employee);
        log.info("Payment Object: {}", new ObjectMapper().writeValueAsString(payment));
        return paymentService.save(employee);
    }

    // get employee by id rest api
    @GetMapping("/payments/{id}")
    public ResponseEntity <Payment> getPaymentById(@PathVariable Long id) {
        Payment employee = paymentService.findById(id);
        return ResponseEntity.ok(employee);
    }

    // update employee rest api

    @PutMapping("/payments/{id}")
    public ResponseEntity <Payment> updatePayment(@PathVariable Long id, @RequestBody Payment payment) {
        Payment employee = paymentService.findById(id);

        employee.setPaymentMode(payment.getPaymentMode());
        employee.setPaymentStatus(payment.getPaymentStatus());
        employee.setTransactionId(payment.getTransactionId());

        Payment updatedEmployee = paymentService.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    // delete employee rest api
    @DeleteMapping("/payments/{id}")
    public ResponseEntity < Map < String, Boolean >> deletePayment(@PathVariable Long id) {
        Payment employee = paymentService.findById(id);

        paymentService.delete(employee);
        Map< String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}