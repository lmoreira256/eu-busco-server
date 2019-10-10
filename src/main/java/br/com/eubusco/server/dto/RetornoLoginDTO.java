package br.com.eubusco.server.dto;

public class RetornoLoginDTO {

	private Integer idUsuario;
	private Boolean logado;
	private Integer tipoUsuario;
	private String nomeUsuario;

	public RetornoLoginDTO(Integer idUsuario, Boolean logado, Integer tipoUsuario, String nomeUsuario) {
		super();
		this.logado = logado;
		this.tipoUsuario = tipoUsuario;
		this.nomeUsuario = nomeUsuario;
		this.idUsuario = idUsuario;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Boolean getLogado() {
		return logado;
	}

	public void setLogado(Boolean logado) {
		this.logado = logado;
	}

	public Integer getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

}
