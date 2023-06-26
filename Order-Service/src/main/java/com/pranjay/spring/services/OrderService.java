package com.pranjay.spring.services;

import com.pranjay.spring.exceptions.ResourceNotFoundException;
import com.pranjay.spring.models.Order;
import com.pranjay.spring.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order save(Order employee) {
        return orderRepository.save(employee);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not exist with id :" + id));
    }

    public void delete(Order employee) {
        orderRepository.delete(employee);
    }
}