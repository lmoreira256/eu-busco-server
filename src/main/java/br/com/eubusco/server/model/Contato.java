package br.com.eubusco.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "tb_contato")
@SequenceGenerator(name = "gen_contato", sequenceName = "gen_contato", allocationSize = 1)
public class Contato implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_contato")
	@GeneratedValue(generator = "gen_contato", strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "cd_tipocontato")
	private Integer codigoTipoContato;

	@Column(name = "cd_usuario")
	private Integer codigoUsuario;

	@Column(name = "tx_descricao")
	private String descricao;

	public Contato() {
	}

	public Contato(Integer codigoTipoContato, Integer codigoUsuario, String descricao) {
		super();
		this.codigoTipoContato = codigoTipoContato;
		this.codigoUsuario = codigoUsuario;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigoTipoContato() {
		return codigoTipoContato;
	}

	public void setCodigoTipoContato(Integer codigoTipoContato) {
		this.codigoTipoContato = codigoTipoContato;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
