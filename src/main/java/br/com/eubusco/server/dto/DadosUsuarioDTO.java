package br.com.eubusco.server.dto;

public class DadosUsuarioDTO {

	private Long nota;
	private Long entregasAbertas;
	private Long totalEntregas;

	public DadosUsuarioDTO() {
	}

	public DadosUsuarioDTO(Long nota, Long entregasAbertas, Long totalEntregas) {
		super();
		this.nota = nota;
		this.entregasAbertas = entregasAbertas;
		this.totalEntregas = totalEntregas;
	}

	public Long getNota() {
		return nota;
	}

	public void setNota(Long nota) {
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
