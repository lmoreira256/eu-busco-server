package br.com.eubusco.server.dao;

import br.com.eubusco.server.model.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {

	public abstract Usuario buscarPorLogin(String login);

	public abstract String buscarNomeUsuario(Integer codigoUsuario);

	public abstract Integer buscarTipoUsuario(Integer codigoUsuario);

}
