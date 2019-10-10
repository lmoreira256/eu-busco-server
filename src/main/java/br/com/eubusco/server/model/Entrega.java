package br.com.eubusco.server.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "tb_entrega")
@SequenceGenerator(name = "gen_entrega", sequenceName = "gen_entrega", allocationSize = 1)
public class Entrega implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_entrega")
	@GeneratedValue(generator = "gen_entrega", strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "cd_cliente")
	private Integer codigoCliente;

	@Column(name = "cd_entregador")
	private Integer codigoEntregador;

	@Column(name = "cd_enderecocoleta")
	private Integer codigoEnderecoColeta;

	@Column(name = "cd_enderecoentrega")
	private Integer codigoEnderecoEntrega;

	@Column(name = "tx_titulo")
	private String titulo;

	@Column(name = "tx_descricao")
	private String descricao;

	@Column(name = "tx_foto")
	private String foto;

	@Column(name = "fl_finalizada")
	private Boolean flagFinalizada;

	@Column(name = "dt_coleta")
	private Date dataColeta;

	@Column(name = "dt_prazoentrega")
	private Date dataPrazoEntrega;

	@Column(name = "dt_cadastro")
	private Date dataCadastro;

	@Column(name = "dt_manutencao")
	private Date dataManutencao;

	@Column(name = "dt_exclusao")
	private Date dataExclusao;

	public Entrega() {
	}

	public Entrega(Integer codigoCliente, Integer codigoEntregador, Integer codigoEnderecoColeta,
			Integer codigoEnderecoEntrega, String titulo, String descricao, String foto, Boolean flagFinalizada,
			Date dataColeta, Date dataPrazoEntrega, Date dataCadastro, Date dataManutencao, Date dataExclusao) {
		super();
		this.codigoCliente = codigoCliente;
		this.codigoEntregador = codigoEntregador;
		this.codigoEnderecoColeta = codigoEnderecoColeta;
		this.codigoEnderecoEntrega = codigoEnderecoEntrega;
		this.titulo = titulo;
		this.descricao = descricao;
		this.foto = foto;
		this.flagFinalizada = flagFinalizada;
		this.dataColeta = dataColeta;
		this.dataPrazoEntrega = dataPrazoEntrega;
		this.dataCadastro = dataCadastro;
		this.dataManutencao = dataManutencao;
		this.dataExclusao = dataExclusao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public Integer getCodigoEntregador() {
		return codigoEntregador;
	}

	public void setCodigoEntregador(Integer codigoEntregador) {
		this.codigoEntregador = codigoEntregador;
	}

	public Integer getCodigoEnderecoColeta() {
		return codigoEnderecoColeta;
	}

	public void setCodigoEnderecoColeta(Integer codigoEnderecoColeta) {
		this.codigoEnderecoColeta = codigoEnderecoColeta;
	}

	public Integer getCodigoEnderecoEntrega() {
		return codigoEnderecoEntrega;
	}

	public void setCodigoEnderecoEntrega(Integer codigoEnderecoEntrega) {
		this.codigoEnderecoEntrega = codigoEnderecoEntrega;
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Boolean getFlagFinalizada() {
		return flagFinalizada;
	}

	public void setFlagFinalizada(Boolean flagFinalizada) {
		this.flagFinalizada = flagFinalizada;
	}

	public Date getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}

	public Date getDataPrazoEntrega() {
		return dataPrazoEntrega;
	}

	public void setDataPrazoEntrega(Date dataPrazoEntrega) {
		this.dataPrazoEntrega = dataPrazoEntrega;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataManutencao() {
		return dataManutencao;
	}

	public void setDataManutencao(Date dataManutencao) {
		this.dataManutencao = dataManutencao;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

}
