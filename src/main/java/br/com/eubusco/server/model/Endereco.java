package br.com.eubusco.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "tb_endereco")
@SequenceGenerator(name = "gen_endereco", sequenceName = "gen_endereco", allocationSize = 1)
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_endereco")
	@GeneratedValue(generator = "gen_endereco", strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "cd_cidade")
	private Integer codigoCidade;

	@Column(name = "cd_usuario")
	private Integer codigoUsuario;

	@Column(name = "tx_endereco")
	private String descricaoEndereco;

	@Column(name = "tx_titulo")
	private String titulo;

	@Column(name = "tx_complemento")
	private String complemento;

	@Column(name = "tx_bairro")
	private String bairro;

	@Column(name = "nr_numero")
	private Long numero;

	public Endereco() {
	}

	public Endereco(Integer codigoCidade, Integer codigoUsuario, String descricaoEndereco, String titulo,
			String complemento, String bairro, Long numero) {
		super();
		this.codigoCidade = codigoCidade;
		this.codigoUsuario = codigoUsuario;
		this.descricaoEndereco = descricaoEndereco;
		this.titulo = titulo;
		this.complemento = complemento;
		this.bairro = bairro;
		this.numero = numero;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCodigoCidade() {
		return codigoCidade;
	}

	public void setCodigoCidade(Integer codigoCidade) {
		this.codigoCidade = codigoCidade;
	}

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getDescricaoEndereco() {
		return descricaoEndereco;
	}

	public void setDescricaoEndereco(String descricaoEndereco) {
		this.descricaoEndereco = descricaoEndereco;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

}
