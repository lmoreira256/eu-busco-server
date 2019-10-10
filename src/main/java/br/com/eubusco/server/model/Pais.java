package br.com.eubusco.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "tb_pais")
@SequenceGenerator(name = "gen_pais", sequenceName = "gen_pais", allocationSize = 1)
public class Pais implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_pais")
	@GeneratedValue(generator = "gen_pais", strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "tx_sigla")
	private String sigla;

	@Column(name = "tx_descricao")
	private String descricao;

	public Pais() {
	}

	public Pais(String sigla, String descricao) {
		super();
		this.sigla = sigla;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
