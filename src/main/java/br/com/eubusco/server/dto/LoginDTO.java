package br.com.eubusco.server.dto;

public class LoginDTO {

	private String login;
	private String senha;

	public LoginDTO() {
	}

	public LoginDTO(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
