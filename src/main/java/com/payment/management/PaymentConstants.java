package com.payment.management;

import java.util.List;

/**
 * @author Rhythm
 *
 */

public class PaymentConstants {

	public static List<String> allowedCurrency = List.of("$","₹","€","¥","£");
	
	public enum ValidationError {
		
		TOTAL_AMOUNT("totalAmount"),NO_OF_PAYMENTS("NoOfPayments"),OK(""),PLAN_ID("Plan Id");
		
		private String name;

		private ValidationError(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
		
	}
	
	public enum Type {
		ERROR,SUCCESS,OK
	}
	
	

}
