package br.com.eubusco.server.dto;

public class ParametroPegarEntregaDTO {

	private Integer codigoEntrega;
	private Integer codigoEntregador;

	public ParametroPegarEntregaDTO() {
	}

	public ParametroPegarEntregaDTO(Integer codigoEntrega, Integer codigoEntregador) {
		super();
		this.codigoEntrega = codigoEntrega;
		this.codigoEntregador = codigoEntregador;
	}

	public Integer getCodigoEntrega() {
		return codigoEntrega;
	}

	public void setCodigoEntrega(Integer codigoEntrega) {
		this.codigoEntrega = codigoEntrega;
	}

	public Integer getCodigoEntregador() {
		return codigoEntregador;
	}

	public void setCodigoEntregador(Integer codigoEntregador) {
		this.codigoEntregador = codigoEntregador;
	}

}
