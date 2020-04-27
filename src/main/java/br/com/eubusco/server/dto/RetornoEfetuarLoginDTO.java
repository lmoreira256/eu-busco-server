package br.com.eubusco.server.dto;

import java.math.BigDecimal;

public class RetornoEfetuarLoginDTO {

	private Integer codigoUsuario;
	private String nomeUsuario;
	private Integer tipoUsuario;
	private BigDecimal nota;
	private RetornoBuscarEntregasDTO entregas;

	public RetornoEfetuarLoginDTO() {
	}

	public RetornoEfetuarLoginDTO(Integer codigoUsuario, String nomeUsuario, Integer tipoUsuario, BigDecimal nota,
			RetornoBuscarEntregasDTO entregas) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.nomeUsuario = nomeUsuario;
		this.tipoUsuario = tipoUsuario;
		this.nota = nota;
		this.entregas = entregas;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Integer getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public BigDecimal getNota() {
		return nota;
	}

	public void setNota(BigDecimal nota) {
		this.nota = nota;
	}

	public RetornoBuscarEntregasDTO getEntregas() {
		return entregas;
	}

	public void setEntregas(RetornoBuscarEntregasDTO entregas) {
		this.entregas = entregas;
	}

}
