package br.com.fs.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2843174409662548927L;

	public ResourceNotFoundException(String pMensagem) {
		super(pMensagem);
	}

	public ResourceNotFoundException(String pMensagem, Throwable t) {
		super(pMensagem, t);
	}

}