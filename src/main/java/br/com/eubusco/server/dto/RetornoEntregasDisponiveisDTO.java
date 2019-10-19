package br.com.eubusco.server.dto;

import java.util.Date;

import br.com.eubusco.server.model.Endereco;

public class RetornoEntregasDisponiveisDTO {

	private String nomeCliente;
	private Endereco enderecoColeta;
	private Endereco enderecoEntrega;
	private String tituloEntrega;
	private String descricaoEntrega;
	private Date dataColetaEntrega;
	private Date dataPrazoEntrega;
	private String volumeEntrega;

	public RetornoEntregasDisponiveisDTO() {
	}

	public RetornoEntregasDisponiveisDTO(String nomeCliente, Endereco enderecoColeta, Endereco enderecoEntrega,
			String tituloEntrega, String descricaoEntrega, Date dataColetaEntrega, Date dataPrazoEntrega,
			String volumeEntrega) {
		super();
		this.nomeCliente = nomeCliente;
		this.enderecoColeta = enderecoColeta;
		this.enderecoEntrega = enderecoEntrega;
		this.tituloEntrega = tituloEntrega;
		this.descricaoEntrega = descricaoEntrega;
		this.dataColetaEntrega = dataColetaEntrega;
		this.dataPrazoEntrega = dataPrazoEntrega;
		this.volumeEntrega = volumeEntrega;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Endereco getEnderecoColeta() {
		return enderecoColeta;
	}

	public void setEnderecoColeta(Endereco enderecoColeta) {
		this.enderecoColeta = enderecoColeta;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public String getTituloEntrega() {
		return tituloEntrega;
	}

	public void setTituloEntrega(String tituloEntrega) {
		this.tituloEntrega = tituloEntrega;
	}

	public String getDescricaoEntrega() {
		return descricaoEntrega;
	}

	public void setDescricaoEntrega(String descricaoEntrega) {
		this.descricaoEntrega = descricaoEntrega;
	}

	public Date getDataColetaEntrega() {
		return dataColetaEntrega;
	}

	public void setDataColetaEntrega(Date dataColetaEntrega) {
		this.dataColetaEntrega = dataColetaEntrega;
	}

	public Date getDataPrazoEntrega() {
		return dataPrazoEntrega;
	}

	public void setDataPrazoEntrega(Date dataPrazoEntrega) {
		this.dataPrazoEntrega = dataPrazoEntrega;
	}

	public String getVolumeEntrega() {
		return volumeEntrega;
	}

	public void setVolumeEntrega(String volumeEntrega) {
		this.volumeEntrega = volumeEntrega;
	}

}
