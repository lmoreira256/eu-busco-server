package br.com.eubusco.server.dao;

import java.util.List;

import br.com.eubusco.server.dto.EntregaDTO;
import br.com.eubusco.server.model.Entrega;

public interface EntregaDAO extends GenericDAO<Entrega> {

	public abstract List<Entrega> buscarEntregasAvaliacao(Integer codigoUsuario, Integer codigoTipoUsuario);

	public abstract Integer buscarCodigoCliente(Integer codigoEntrega);

	public abstract Integer buscarCodigoEntregador(Integer codigoEntrega);

	public abstract List<EntregaDTO> buscarEntregasAbertas(Integer codigoUsuario, Integer tipoUsuario, Integer pagina);

	public abstract Long buscarTotalEntregasAbertas(Integer codigoUsuario, Integer tipoUsuario);

	public abstract List<EntregaDTO> buscarEntregasAndamento(Integer codigoUsuario, Integer tipoUsuario,
			Integer pagina);

	public abstract Long buscarTotalEntregasAndamento(Integer codigoUsuario, Integer tipoUsuario);

	public abstract List<EntregaDTO> buscarEntregasFinalizadas(Integer codigoUsuario, Integer tipoUsuario,
			Integer pagina);

	public abstract Long buscarTotalEntregasFinalizadas(Integer codigoUsuario, Integer tipoUsuario);

	public abstract List<EntregaDTO> buscarEntregasExcluidas(Integer codigoUsuario, Integer tipoUsuario,
			Integer pagina);

	public abstract Long buscarTotalEntregasExcluidas(Integer codigoUsuario, Integer tipoUsuario);

}
