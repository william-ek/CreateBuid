package com.deloitte.bu.exception;

import java.util.List;

import org.springframework.stereotype.Component;

import com.deloitte.bu.models.ResponseDto;

@Component
public class RaiseException {

	public ResponseDto raiseException(String message) {
		ResponseDto response = new ResponseDto(
					null,
					"99",
					"Unsuccessful - Error " + message,
					null
				);
		return response;
	}
	
	public ResponseDto raiseValidationErrors(List<String> errors) {
		ResponseDto response = new ResponseDto(
				null,
				"99",
				"Unsuccessful - Missing Parameters",
				null
			);
		response.setErrors(errors);
		return response;
	}
}
