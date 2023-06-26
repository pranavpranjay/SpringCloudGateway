
package com.pranjay.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private long paymentId;

    private String paymentStatus;

    private String transactionId;

    private String paymentMode;

    private long orderId;

    private double amount;

}
