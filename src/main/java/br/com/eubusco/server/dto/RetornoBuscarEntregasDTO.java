package br.com.eubusco.server.dto;

public class RetornoBuscarEntregasDTO {

	private PaginacaoDTO abertas;
	private PaginacaoDTO andamento;
	private PaginacaoDTO finalizadas;

	public RetornoBuscarEntregasDTO() {
	}

	public RetornoBuscarEntregasDTO(PaginacaoDTO abertas, PaginacaoDTO andamento, PaginacaoDTO finalizadas) {
		super();
		this.abertas = abertas;
		this.andamento = andamento;
		this.finalizadas = finalizadas;
	}

	public PaginacaoDTO getAbertas() {
		return abertas;
	}

	public void setAbertas(PaginacaoDTO abertas) {
		this.abertas = abertas;
	}

	public PaginacaoDTO getAndamento() {
		return andamento;
	}

	public void setAndamento(PaginacaoDTO andamento) {
		this.andamento = andamento;
	}

	public PaginacaoDTO getFinalizadas() {
		return finalizadas;
	}

	public void setFinalizadas(PaginacaoDTO finalizadas) {
		this.finalizadas = finalizadas;
	}

}
