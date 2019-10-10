package br.com.eubusco.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "tb_cidade")
@SequenceGenerator(name = "gen_cidade", sequenceName = "gen_cidade", allocationSize = 1)
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_cidade")
	@GeneratedValue(generator = "gen_cidade", strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "cd_estado")
	private Integer codigoEstado;

	@Column(name = "nr_cep")
	private Long cep;

	@Column(name = "tx_sigla")
	private String sigla;

	@Column(name = "tx_descricao")
	private String descricao;

	public Cidade() {
	}

	public Cidade(Integer codigoEstado, Long cep, String sigla, String descricao) {
		super();
		this.codigoEstado = codigoEstado;
		this.cep = cep;
		this.sigla = sigla;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(Integer codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
