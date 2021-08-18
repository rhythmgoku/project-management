package com.payment.management.dto;

import java.math.BigDecimal;

/**
 * @author Rhythm
 *
 */

public class PaymentDTO {

    private Integer paymentId;
	private BigDecimal totalAmount;
	private int numberOfPayments;
	private BigDecimal regularPaymentAmount;
	private BigDecimal lastAmount;
	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getNumberOfPayments() {
		return numberOfPayments;
	}
	public void setNumberOfPayments(int numberOfPayments) {
		this.numberOfPayments = numberOfPayments;
	}
	public BigDecimal getRegularPaymentAmount() {
		return regularPaymentAmount;
	}
	public void setRegularPaymentAmount(BigDecimal regularPaymentAmount) {
		this.regularPaymentAmount = regularPaymentAmount;
	}
	public BigDecimal getLastAmount() {
		return lastAmount;
	}
	public void setLastAmount(BigDecimal lastAmount) {
		this.lastAmount = lastAmount;
	}
	
	
	
}
