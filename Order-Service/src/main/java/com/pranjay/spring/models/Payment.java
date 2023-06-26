package com.pranjay.spring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PAYMENT_TB")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "payment_mode")
    private String paymentMode;

    @Column(name = "order_id")
    private long orderId;

    @Column(name = "amount")
    private double amount;

}
