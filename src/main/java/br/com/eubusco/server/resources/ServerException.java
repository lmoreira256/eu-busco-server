package br.com.eubusco.server.resources;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ServerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String error;

	private final String mensagem;

	public ServerException(String error, String mensagem) {
		super(mensagem);
		this.error = error;
		this.mensagem = mensagem;

	}

	public ServerException(String error, String mensagem, Throwable throwable) {
		super(mensagem, throwable);
		this.error = error;
		this.mensagem = mensagem;

	}

	public String getMensagem() {
		return mensagem;
	}

	public String getMensagemTecnica() {
		return super.getMessage();
	}

	public String getError() {
		return error;
	}

}
