package br.com.eubusco.server.dao;

import br.com.eubusco.server.model.Usuario;

public interface UserDAO extends GenericDAO<Usuario> {

	public abstract Usuario buscarPorLogin(String login);

	public abstract String getNameFromUser(Integer userCode);

}
