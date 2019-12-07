package br.com.eubusco.server.dao;

import java.util.List;

import br.com.eubusco.server.model.Entrega;

public interface EntregaDAO extends GenericDAO<Entrega> {

	public abstract Long adquirirEntregasAbertasUsuario(Integer idUsuario);

	public abstract Long adquirirTotalEntregasUsuario(Integer idUsuario);

	public abstract List<Entrega> buscarAbertasCliente(Integer idUsuario);

	public abstract List<Entrega> buscarAbertasEntregador(Integer idUsuario);

	public abstract List<Entrega> buscarDisponiveis();

	public abstract List<Entrega> buscarTodasAbertas();

	public abstract List<Entrega> buscarEntregasAvaliacao(Integer codigoUsuario, Integer codigoTipoUsuario);

	public abstract Integer buscarCodigoCliente(Integer codigoEntrega);

	public abstract Integer buscarCodigoEntregador(Integer codigoEntrega);

}
