package br.com.fs.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

	@ExceptionHandler(ParametroObrigatorioException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public <T> ResponseEntity<T> handleCustomExceptionServerError(ParametroObrigatorioException ex) {
		LOGGER.error("ParametroObrigatorioException: " + ex.getMessage());
		if (ex.getBindingResult() != null)
			return new ResponseEntity<T>((T) ex.getBindingResult().getAllErrors(), HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<T>((T) ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
