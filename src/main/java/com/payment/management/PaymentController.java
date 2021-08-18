package com.payment.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payment.management.dto.CommonResponse;
import com.payment.management.dto.PaymentDTO;
import com.payment.management.service.PaymentService;

/**
 * @author Rhythm
 *
 */

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	/**
	 * @param totalAmount
	 * @param noOfPayments
	 * @return PaymentDTO ( Payment Plan generated based on the provided inputs)
	 * @throws Exception
	 */
	@PostMapping("/generatePlan")
	public ResponseEntity<PaymentDTO> generatePlan(
			@RequestParam(name = "totalAmount", required = true) String totalAmount,
			@RequestParam(name = "NoOfPayments", required = true) String noOfPayments) throws Exception {

		Pair<Double, Integer> data = null;

//	    logic for validating the input values 
		data = PaymentUtil.validateInputData(totalAmount, noOfPayments);

		PaymentDTO paymentVO = paymentService.generatePlan(data.getFirst(), data.getSecond());
		return new ResponseEntity<>(paymentVO, HttpStatus.CREATED);
	}

	/**
	 * @param id
	 * @return Custom Response based on the Id value -> If Id Present specific
	 *         Payment Plan will be returned else all Payment Plans will be returned
	 * @throws Exception
	 */
	@GetMapping(value = { "/plans", "/plans/{id}" })
	public ResponseEntity<?> getPlans(@PathVariable(name = "id", required = false) String id) throws Exception {
		if (null != id && !id.isEmpty()) {

//		    logic for validating the input values 
			Integer paymentId = PaymentUtil.validateInputData(id);
			return new ResponseEntity(paymentService.getPlanById(paymentId), HttpStatus.OK);
		} else {
			return new ResponseEntity(paymentService.getAllPlans(), HttpStatus.OK);
		}
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<CommonResponse> handleExceptions(Exception exception) {
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setType(PaymentConstants.Type.ERROR.toString());
		commonResponse.setMessage(exception.getMessage());
		return new ResponseEntity<>(commonResponse, HttpStatus.EXPECTATION_FAILED);
	}

}
