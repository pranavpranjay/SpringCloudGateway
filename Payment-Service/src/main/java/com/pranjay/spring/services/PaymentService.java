package com.pranjay.spring.services;

import com.pranjay.spring.exceptions.ResourceNotFoundException;
import com.pranjay.spring.models.Payment;
import com.pranjay.spring.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository employeeRepository;

    public List<Payment> findAll() {
        return employeeRepository.findAll();
    }

    public Payment save(Payment payment) {
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setPaymentStatus("success");
        return employeeRepository.save(payment);
    }

    public Payment findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
    }

    public void delete(Payment employee) {
        employeeRepository.delete(employee);
    }
}

