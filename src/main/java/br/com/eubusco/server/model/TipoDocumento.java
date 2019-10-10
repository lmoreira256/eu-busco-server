package br.com.eubusco.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "tb_tipodocumento")
@SequenceGenerator(name = "gen_tipodocumento", sequenceName = "gen_tipodocumento", allocationSize = 1)
public class TipoDocumento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_tipodocumento")
	@GeneratedValue(generator = "gen_tipodocumento", strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "tx_descricao")
	private String descricao;

	public TipoDocumento() {
	}

	public TipoDocumento(String descricao) {
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
