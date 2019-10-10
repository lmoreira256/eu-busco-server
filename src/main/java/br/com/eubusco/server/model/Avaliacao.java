package br.com.eubusco.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "tb_avaliacao")
@SequenceGenerator(name = "gen_avaliacao", sequenceName = "gen_avaliacao", allocationSize = 1)
public class Avaliacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(generator = "gen_avaliacao", strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "cd_entregador")
	private Integer codigoEntregador;

	@Column(name = "cd_cliente")
	private Integer codigoCliente;

	@Column(name = "cd_entrega")
	private Integer codigoEntrega;

	@Column(name = "cd_tipoavaliacao")
	private Integer codigoTipoAvaliacao;

	@Column(name = "tx_descricao")
	private String descricao;

	@Column(name = "nr_nota")
	private Long nota;

	public Avaliacao() {
	}

	public Avaliacao(Integer codigoEntregador, Integer codigoCliente, Integer codigoEntrega,
			Integer codigoTipoAvaliacao, String descricao, Long nota) {
		super();
		this.codigoEntregador = codigoEntregador;
		this.codigoCliente = codigoCliente;
		this.codigoEntrega = codigoEntrega;
		this.codigoTipoAvaliacao = codigoTipoAvaliacao;
		this.descricao = descricao;
		this.nota = nota;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigoEntregador() {
		return codigoEntregador;
	}

	public void setCodigoEntregador(Integer codigoEntregador) {
		this.codigoEntregador = codigoEntregador;
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public Integer getCodigoEntrega() {
		return codigoEntrega;
	}

	public void setCodigoEntrega(Integer codigoEntrega) {
		this.codigoEntrega = codigoEntrega;
	}

	public Integer getCodigoTipoAvaliacao() {
		return codigoTipoAvaliacao;
	}

	public void setCodigoTipoAvaliacao(Integer codigoTipoAvaliacao) {
		this.codigoTipoAvaliacao = codigoTipoAvaliacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getNota() {
		return nota;
	}

	public void setNota(Long nota) {
		this.nota = nota;
	}

}
