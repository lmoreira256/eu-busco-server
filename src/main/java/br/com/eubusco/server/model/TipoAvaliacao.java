package br.com.eubusco.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "tb_tipoavaliacao")
@SequenceGenerator(name = "gen_tipoavaliacao", sequenceName = "gen_tipoavaliacao", allocationSize = 1)
public class TipoAvaliacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_tipoavaliacao")
	@GeneratedValue(generator = "gen_tipoavaliacao", strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "tx_descricao")
	private String descricao;

	public TipoAvaliacao() {
	}

	public TipoAvaliacao(String descricao) {
		super();
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
