package br.com.eubusco.server.dto;

import java.math.BigDecimal;

public class DadosUsuarioDTO {

	private BigDecimal nota;
	private Long entregasAbertas;
	private Long totalEntregas;

	public DadosUsuarioDTO() {
	}

	public DadosUsuarioDTO(BigDecimal nota, Long entregasAbertas, Long totalEntregas) {
		super();
		this.nota = nota;
		this.entregasAbertas = entregasAbertas;
		this.totalEntregas = totalEntregas;
	}

	public BigDecimal getNota() {
		return nota;
	}

	public void setNota(BigDecimal nota) {
		this.nota = nota;
	}

	public Long getEntregasAbertas() {
		return entregasAbertas;
	}

	public void setEntregasAbertas(Long entregasAbertas) {
		this.entregasAbertas = entregasAbertas;
	}

	public Long getTotalEntregas() {
		return totalEntregas;
	}

	public void setTotalEntregas(Long totalEntregas) {
		this.totalEntregas = totalEntregas;
	}

}
