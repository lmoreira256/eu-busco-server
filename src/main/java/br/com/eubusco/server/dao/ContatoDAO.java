package br.com.eubusco.server.dao;

import java.util.List;

import br.com.eubusco.server.model.Contato;

public interface ContatoDAO extends GenericDAO<Contato> {

	public abstract List<Contato> buscarContatosUsuario(Integer codigoUsuario);

}
