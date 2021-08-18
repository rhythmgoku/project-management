package com.payment.management.dao;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Rhythm
 *
 */
@Entity
@Table(name = "payment")
public class PaymentDAO {

	@Id
	@Column(name="Payment_Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paymentId;
	
	@Column(name="Total_Amount" ,nullable = false)
	private BigDecimal totalAmount;
	
	@Column(name="No_Of_Payments",nullable = false)
	private int numberOfPayments;
	
	@Column(name="Regular_Payemnt_Amount",nullable = false)
	private BigDecimal regularPaymentAmount;
	
	@Column(name="Last_Payemnt_Amount",nullable = true)
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
