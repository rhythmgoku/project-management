package com.payment.management.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.management.PaymentUtil;
import com.payment.management.dao.PaymentDAO;
import com.payment.management.dao.PaymentRepository;
import com.payment.management.dto.PaymentDTO;

/**
 * @author Rhythm
 *
 */

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public PaymentDTO generatePlan(Double amount, Integer noOfPayments) {

		Double splits = amount / noOfPayments;
//		using Bigdecimal to properly scale and round the value to 2 decimal places
//		Rounding down for normalPayments
		BigDecimal normalPaymentValue = new BigDecimal(splits).setScale(2, BigDecimal.ROUND_DOWN);
		double normalPayments = normalPaymentValue.doubleValue();
		
		Double remainingPayment = amount - (normalPayments * noOfPayments);

		PaymentDTO paymentDTO = new PaymentDTO();
		
//		Rounding up for TotalAmount
		paymentDTO.setTotalAmount(new BigDecimal(amount).setScale(2, BigDecimal.ROUND_UP));
		paymentDTO.setNumberOfPayments(noOfPayments);
		paymentDTO.setRegularPaymentAmount(normalPaymentValue);

		if (remainingPayment != 0) {
			
//		Rounding up for lastPayment
			BigDecimal lastPayment = new BigDecimal(normalPayments + remainingPayment).setScale(2, BigDecimal.ROUND_UP);
			paymentDTO.setLastAmount(lastPayment);
		}

//		Saving the passed info after processing into the DB
		PaymentDTO savedPayment = savePayment(paymentDTO);

		return savedPayment;
	}

	private PaymentDTO savePayment(PaymentDTO paymentDTO) {
//		dealing with only DAO in the database Layer
		PaymentDAO paymentDAOPre = PaymentUtil.dtoToDaoConversion(paymentDTO);

//		getting the saved data out in DTO form
		PaymentDAO paymentDAOPost = paymentRepository.save(paymentDAOPre);
//		re-conversion of DAO to DTO 
		return PaymentUtil.daoToDtoConversion(paymentDAOPost);
	}

	@Override
	public PaymentDTO getPlanById(Integer id) {
//		calling upon the direct JPA findbyId method available
		PaymentDAO paymentDAO = paymentRepository.findById(id).get();
		return PaymentUtil.daoToDtoConversion(paymentDAO);
	}

	@Override
	public List<PaymentDTO> getAllPlans() {
		List<PaymentDAO> paymentDAOs = findAll();
		
//		converting the List of DAO to List of DTO for transferring to other layers
		return paymentDAOs.stream().map(PaymentUtil::daoToDtoConversion).collect(Collectors.toList());
	}
	
	private List<PaymentDAO> findAll() {
		List<PaymentDAO> paymentDAOs= new ArrayList<>();
		
//		converting the iterable from findAll to List of DAO
		paymentRepository.findAll().forEach(paymentDAOs :: add);
		return paymentDAOs;
	}

}
