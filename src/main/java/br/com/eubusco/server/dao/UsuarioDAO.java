package br.com.eubusco.server.dao;

import br.com.eubusco.server.model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {

	public abstract Usuario buscarPorLogin(String login);

	public abstract String buscarNomePorId(Integer idUsuario);

}
