package br.com.eubusco.server.dao;

import br.com.eubusco.server.model.Entrega;

public interface EntregaDAO extends GenericDAO<Entrega> {

	public abstract Long adquirirEntragasAbertasUsuario(Integer codigoUsuario);

	public abstract Long adquirirTotalEntragasUsuario(Integer codigoUsuario);

}
