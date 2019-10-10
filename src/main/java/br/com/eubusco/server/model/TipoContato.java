package br.com.eubusco.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "tb_tipocontato")
@SequenceGenerator(name = "gen_tipocontato", sequenceName = "tipocontato", allocationSize = 1)
public class TipoContato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_tipocontato")
	@GeneratedValue(generator = "gen_tipocontato", strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "tx_descricao")
	private String descricao;

	public TipoContato() {
	}

	public TipoContato(String descricao) {
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
