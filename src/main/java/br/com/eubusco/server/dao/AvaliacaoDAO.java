package br.com.eubusco.server.dao;

import br.com.eubusco.server.model.Avaliacao;

public interface AvaliacaoDAO extends GenericDAO<Avaliacao> {

	public abstract Long adiquirirNotaUsuario(Integer codigoUsuario);

}
