package br.com.eubusco.server.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.eubusco.server.model.Contato;
import br.com.eubusco.server.model.Documento;
import br.com.eubusco.server.model.Endereco;

public class NovoUsuarioDTO {

	private Integer tipoUsuario;
	private String nomeUsuario;
	private String senhaUsuario;
	private String loginUsuario;
	private MultipartFile fotoUsuario;
	private Date dataNascimento;
	private List<Documento> documentosUsuario;
	private List<Endereco> enderecosUsuario;
	private List<Contato> contatosUsuario;

	public NovoUsuarioDTO() {
	}

	public NovoUsuarioDTO(Integer tipoUsuario, String nomeUsuario, String senhaUsuario, String loginUsuario,
			MultipartFile fotoUsuario, Date dataNascimento, List<Documento> documentosUsuario,
			List<Endereco> enderecosUsuario, List<Contato> contatosUsuario) {
		super();
		this.tipoUsuario = tipoUsuario;
		this.nomeUsuario = nomeUsuario;
		this.senhaUsuario = senhaUsuario;
		this.loginUsuario = loginUsuario;
		this.fotoUsuario = fotoUsuario;
		this.dataNascimento = dataNascimento;
		this.documentosUsuario = documentosUsuario;
		this.enderecosUsuario = enderecosUsuario;
		this.contatosUsuario = contatosUsuario;
	}

	public Integer getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public MultipartFile getFotoUsuario() {
		return fotoUsuario;
	}

	public void setFotoUsuario(MultipartFile fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Documento> getDocumentosUsuario() {
		return documentosUsuario;
	}

	public void setDocumentosUsuario(List<Documento> documentosUsuario) {
		this.documentosUsuario = documentosUsuario;
	}

	public List<Endereco> getEnderecosUsuario() {
		return enderecosUsuario;
	}

	public void setEnderecosUsuario(List<Endereco> enderecosUsuario) {
		this.enderecosUsuario = enderecosUsuario;
	}

	public List<Contato> getContatosUsuario() {
		return contatosUsuario;
	}

	public void setContatosUsuario(List<Contato> contatosUsuario) {
		this.contatosUsuario = contatosUsuario;
	}

}
