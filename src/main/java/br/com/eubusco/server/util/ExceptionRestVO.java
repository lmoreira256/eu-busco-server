package br.com.eubusco.server.util;

public class ExceptionRestVO {

	private String error;
	private String mensagem;
	private String mensagemTecnica;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagemTecnica() {
		return mensagemTecnica;
	}

	public void setMensagemTecnica(String mensagemTecnica) {
		this.mensagemTecnica = mensagemTecnica;
	}

	public ExceptionRestVO(String codigo, String mensagem, String mensagemTecnica) {
		this.error = codigo;
		this.mensagem = mensagem;
		this.mensagemTecnica = mensagemTecnica;
	}

}
