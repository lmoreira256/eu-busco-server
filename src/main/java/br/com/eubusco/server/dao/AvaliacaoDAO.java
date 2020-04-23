package br.com.eubusco.server.dao;

import java.math.BigDecimal;

import br.com.eubusco.server.model.Avaliacao;

public interface AvaliacaoDAO extends GenericDAO<Avaliacao> {

	public abstract BigDecimal buscarNotaUsuario(Integer codigoUsuario, Integer tipoUsuario);

}
