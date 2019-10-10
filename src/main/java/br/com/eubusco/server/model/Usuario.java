package br.com.eubusco.server.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "tb_usuario")
@SequenceGenerator(name = "gen_usuario", sequenceName = "gen_usuario", allocationSize = 1)
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(generator = "gen_usuario", strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "cd_tipousuario")
	private Integer codigoTipoUsuario;

	@Column(name = "tx_login")
	private String login;

	@Column(name = "tx_nome")
	private String nome;

	@Column(name = "tx_senha")
	private String senha;

	@Column(name = "tx_foto")
	private String foto;

	@Column(name = "dt_nascimento")
	private Date dataNascimento;

	@Column(name = "dt_cadastro")
	private Date dataCadastro;

	@Column(name = "dt_manutencao")
	private Date dataManutencao;

	@Column(name = "dt_exclusao")
	private Date dataExclusao;

	public Usuario() {
	}

	public Usuario(Integer codigoTipoUsuario, String login, String nome, String senha, String foto, Date dataNascimento,
			Date dataCadastro, Date dataManutencao, Date dataExclusao) {
		super();
		this.codigoTipoUsuario = codigoTipoUsuario;
		this.login = login;
		this.nome = nome;
		this.senha = senha;
		this.foto = foto;
		this.dataNascimento = dataNascimento;
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

	public Integer getCodigoTipoUsuario() {
		return codigoTipoUsuario;
	}

	public void setCodigoTipoUsuario(Integer codigoTipoUsuario) {
		this.codigoTipoUsuario = codigoTipoUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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
