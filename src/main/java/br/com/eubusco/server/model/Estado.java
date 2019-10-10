package br.com.eubusco.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "tb_estado")
@SequenceGenerator(name = "gen_estado", sequenceName = "gen_estado", allocationSize = 1)
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_estado")
	@GeneratedValue(generator = "gen_estado", strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "cd_pais")
	private Integer codigoPais;

	@Column(name = "tx_sigla")
	private String sigla;

	@Column(name = "tx_descricao")
	private String descricao;

	public Estado() {
	}

	public Estado(Integer codigoPais, String sigla, String descricao) {
		super();
		this.codigoPais = codigoPais;
		this.sigla = sigla;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(Integer codigoPais) {
		this.codigoPais = codigoPais;
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
