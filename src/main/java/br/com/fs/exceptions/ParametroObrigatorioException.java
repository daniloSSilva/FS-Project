package br.com.fs.exceptions;

import org.springframework.validation.BindingResult;

public class ParametroObrigatorioException extends Exception {
	private static final long serialVersionUID = 1L;
	private static BindingResult bindingResult;

	public ParametroObrigatorioException() {
		super();
	}

	public ParametroObrigatorioException(String pMensagem) {
		super(pMensagem);
	}

	public ParametroObrigatorioException(Throwable pException) {
		super(pException);
	}

	public ParametroObrigatorioException(String pMensagem, Throwable pException) {
		super(pMensagem, pException);
	}

	public BindingResult getBindingResult() {
		return bindingResult;
	}
}
