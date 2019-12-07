package br.com.eubusco.server.dto;

public class RetornoEntregaAvaliacaoDTO {

	private Integer codigoEntrega;
	private String tituloEntrega;
	private String nomeAvaliado;

	public RetornoEntregaAvaliacaoDTO() {
	}

	public RetornoEntregaAvaliacaoDTO(Integer codigoEntrega, String nomeAvaliado, String tituloEntrega) {
		super();
		this.codigoEntrega = codigoEntrega;
		this.tituloEntrega = tituloEntrega;
		this.nomeAvaliado = nomeAvaliado;
	}

	public Integer getCodigoEntrega() {
		return codigoEntrega;
	}

	public void setCodigoEntrega(Integer codigoEntrega) {
		this.codigoEntrega = codigoEntrega;
	}

	public String getTituloEntrega() {
		return tituloEntrega;
	}

	public void setTituloEntrega(String tituloEntrega) {
		this.tituloEntrega = tituloEntrega;
	}

	public String getNomeAvaliado() {
		return nomeAvaliado;
	}

	public void setNomeAvaliado(String nomeAvaliado) {
		this.nomeAvaliado = nomeAvaliado;
	}

}
