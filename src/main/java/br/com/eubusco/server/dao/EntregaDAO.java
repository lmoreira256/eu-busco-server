package br.com.eubusco.server.dao;

import java.util.List;

import br.com.eubusco.server.model.Entrega;

public interface EntregaDAO extends GenericDAO<Entrega> {

	public abstract Long adquirirEntragasAbertasUsuario(Integer idUsuario);

	public abstract Long adquirirTotalEntragasUsuario(Integer idUsuario);

	public abstract List<Entrega> buscarAbertasCliente(Integer idUsuario);

}
