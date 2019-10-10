package br.com.eubusco.server.resources;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class ServerExceptionUnauthorized extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String codigo;

	private final String mensagem;

	public ServerExceptionUnauthorized(String codigo, String mensagem) {
		super(mensagem);
		this.codigo = codigo;
		this.mensagem = mensagem;

	}

	public ServerExceptionUnauthorized(String codigo, String mensagem, Throwable throwable) {
		super(mensagem, throwable);
		this.codigo = codigo;
		this.mensagem = mensagem;

	}

	public String getCodigo() {
		return codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getMensagemTecnica() {
		return super.getMessage();
	}

}
