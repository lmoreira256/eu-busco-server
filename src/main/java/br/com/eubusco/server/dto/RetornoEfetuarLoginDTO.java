package br.com.eubusco.server.dto;

import java.math.BigDecimal;

public class RetornoEfetuarLoginDTO {

	private Integer codigoUsuario;
	private String nomeUsuario;
	private Integer tipoUsuario;
	private BigDecimal nota;
	private PaginacaoDTO entregasUsuarioAbertas;
	private PaginacaoDTO entregasUsuarioAndamento;
	private PaginacaoDTO entregasAbertas;
	private PaginacaoDTO entregasFinalizadas;

	public RetornoEfetuarLoginDTO() {
	}

	public RetornoEfetuarLoginDTO(Integer codigoUsuario, String nomeUsuario, Integer tipoUsuario, BigDecimal nota,
			PaginacaoDTO entregasUsuarioAbertas, PaginacaoDTO entregasUsuarioAndamento, PaginacaoDTO entregasAbertas,
			PaginacaoDTO entregasFinalizadas) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.nomeUsuario = nomeUsuario;
		this.tipoUsuario = tipoUsuario;
		this.nota = nota;
		this.entregasUsuarioAbertas = entregasUsuarioAbertas;
		this.entregasUsuarioAndamento = entregasUsuarioAndamento;
		this.entregasAbertas = entregasAbertas;
		this.entregasFinalizadas = entregasFinalizadas;
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

	public PaginacaoDTO getEntregasUsuarioAbertas() {
		return entregasUsuarioAbertas;
	}

	public void setEntregasUsuarioAbertas(PaginacaoDTO entregasUsuarioAbertas) {
		this.entregasUsuarioAbertas = entregasUsuarioAbertas;
	}

	public PaginacaoDTO getEntregasUsuarioAndamento() {
		return entregasUsuarioAndamento;
	}

	public void setEntregasUsuarioAndamento(PaginacaoDTO entregasUsuarioAndamento) {
		this.entregasUsuarioAndamento = entregasUsuarioAndamento;
	}

	public PaginacaoDTO getEntregasAbertas() {
		return entregasAbertas;
	}

	public void setEntregasAbertas(PaginacaoDTO entregasAbertas) {
		this.entregasAbertas = entregasAbertas;
	}

	public PaginacaoDTO getEntregasFinalizadas() {
		return entregasFinalizadas;
	}

	public void setEntregasFinalizadas(PaginacaoDTO entregasFinalizadas) {
		this.entregasFinalizadas = entregasFinalizadas;
	}

}
