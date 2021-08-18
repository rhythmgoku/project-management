package com.payment.management.service;

import java.util.List;

import com.payment.management.dto.PaymentDTO;

/**
 * @author Rhythm
 *
 */

public interface PaymentService {
	
	PaymentDTO generatePlan(Double amount,Integer noOfPayments);

	PaymentDTO getPlanById(Integer id);

	List<PaymentDTO> getAllPlans();
}
