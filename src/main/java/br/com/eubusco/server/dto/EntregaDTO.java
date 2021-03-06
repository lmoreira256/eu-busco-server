package br.com.eubusco.server.dto;

public class EntregaDTO {

	private Integer codigo;
	private String titulo;
	private String descricao;
	private String nomeCliente;
	private String nomeEntregador;
	private String cidadeEntrega;
	private String cidadeColeta;
	private String volume;
	private Boolean finalizada;
	private String contatoCliente;
	private String dataColeta;
	private String dataEntrega;
	private String dataExclusao;

	public EntregaDTO() {
	}

	public EntregaDTO(Integer codigo, String titulo, String descricao, String nomeCliente, String nomeEntregador,
			String cidadeEntrega, String cidadeColeta, String volume, Boolean finalizada, String contatoCliente,
			String dataColeta, String dataEntrega, String dataExclusao) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.descricao = descricao;
		this.nomeCliente = nomeCliente;
		this.nomeEntregador = nomeEntregador;
		this.cidadeEntrega = cidadeEntrega;
		this.cidadeColeta = cidadeColeta;
		this.volume = volume;
		this.contatoCliente = contatoCliente;
		this.finalizada = finalizada;
		this.dataColeta = dataColeta;
		this.dataEntrega = dataEntrega;
		this.dataExclusao = dataExclusao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeEntregador() {
		return nomeEntregador;
	}

	public void setNomeEntregador(String nomeEntregador) {
		this.nomeEntregador = nomeEntregador;
	}

	public String getCidadeEntrega() {
		return cidadeEntrega;
	}

	public void setCidadeEntrega(String cidadeEntrega) {
		this.cidadeEntrega = cidadeEntrega;
	}

	public String getCidadeColeta() {
		return cidadeColeta;
	}

	public void setCidadeColeta(String cidadeColeta) {
		this.cidadeColeta = cidadeColeta;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public Boolean getFinalizada() {
		return finalizada;
	}

	public void setFinalizada(Boolean finalizada) {
		this.finalizada = finalizada;
	}

	public String getContatoCliente() {
		return contatoCliente;
	}

	public void setContatoCliente(String contatoCliente) {
		this.contatoCliente = contatoCliente;
	}

	public String getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(String dataColeta) {
		this.dataColeta = dataColeta;
	}

	public String getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(String dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

}
