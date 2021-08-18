package com.payment.management;

import java.util.Optional;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import com.payment.management.PaymentConstants.ValidationError;
import com.payment.management.dao.PaymentDAO;
import com.payment.management.dto.PaymentDTO;

/**
 * @author Rhythm
 *
 */

@Component
public class PaymentUtil {

	/**
	 * validates the passed input and throws exception on error scenarios
	 * 
	 * @param totalAmount
	 * @param noOfPayments
	 * @return Pair of parsedAmount and noOfPayment
	 * @throws Exception
	 */
	public static Pair<Double, Integer> validateInputData(String totalAmount, String noOfPayments) throws Exception {

		Double parsedAmount = null;
		Integer noOfPayment = null;
		try {
			Optional<String> currencyCharcter = PaymentConstants.allowedCurrency.stream().filter(x -> totalAmount.contains(x))
					.findFirst();

			String formattedAmount = "";
			
			if(currencyCharcter.isPresent()) {
				formattedAmount = totalAmount.replaceAll(currencyCharcter.get(), "");
			}else {
				formattedAmount = totalAmount;
			}
			formattedAmount = formattedAmount.replaceAll(",", "").strip();
			parsedAmount = Double.parseDouble(formattedAmount);

		} catch (Exception e) {
			throw new Exception("Invalid " + ValidationError.TOTAL_AMOUNT.name());
		}

		try {
			noOfPayment = Integer.parseInt(noOfPayments);
		} catch (Exception e) {
			throw new Exception("Invalid " + ValidationError.NO_OF_PAYMENTS.name());
		}

		return Pair.of(parsedAmount, noOfPayment);

	}
	
	/**
	 * validates the passed input and throws exception on error scenarios
	 * 
	 * @param paymentId
	 * @return paymentIdParsed
	 * @throws Exception
	 */
	public static Integer validateInputData(String paymentId) throws Exception {
		
		Integer paymentIdParsed = null;
		try {
			paymentIdParsed = Integer.parseInt(paymentId);
		}
		catch(NumberFormatException exception) {
			throw new Exception("Invalid " + ValidationError.PLAN_ID.name());
		}
		return paymentIdParsed;

	}
	
	
	/**
	 * Converter Method for DTO to DAO
	 * 
	 * @param paymentDTO
	 * @return PaymentDAO
	 */
	public static PaymentDAO dtoToDaoConversion(PaymentDTO paymentDTO) {

		PaymentDAO paymentDAO = new PaymentDAO();
		paymentDAO.setTotalAmount(paymentDTO.getTotalAmount());
		paymentDAO.setNumberOfPayments(paymentDTO.getNumberOfPayments());
		paymentDAO.setRegularPaymentAmount(paymentDTO.getRegularPaymentAmount());
		paymentDAO.setLastAmount(paymentDTO.getLastAmount());
		return paymentDAO;

	}

	/**
	 * Converter Method for DAO to DTO
	 * 
	 * @param paymentDAO
	 * @return PaymentDTO
	 */
	public static PaymentDTO daoToDtoConversion(PaymentDAO paymentDAO) {

		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setTotalAmount(paymentDAO.getTotalAmount());
		paymentDTO.setNumberOfPayments(paymentDAO.getNumberOfPayments());
		paymentDTO.setRegularPaymentAmount(paymentDAO.getRegularPaymentAmount());
		paymentDTO.setLastAmount(paymentDAO.getLastAmount());
		paymentDTO.setPaymentId(paymentDAO.getPaymentId());
		return paymentDTO;

	}

}
