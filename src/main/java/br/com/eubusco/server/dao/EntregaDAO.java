package br.com.eubusco.server.dao;

import java.util.List;

import br.com.eubusco.server.dto.EntregaDTO;
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

	public abstract List<EntregaDTO> buscarEntregasUsuarioAbertas(Integer codigoUsuario, Integer pagina);

	public abstract Long buscarTotalEntregasUsuarioAbertas(Integer codigoUsuario);

	public abstract List<EntregaDTO> buscarEntregasUsuarioAndamento(Integer codigoUsuario, Integer pagina);

	public abstract Long buscarTotalEntregasUsuarioAndamento(Integer codigoUsuario);

	public abstract List<EntregaDTO> buscarEntregasParaEntregar(Integer codigoUsuario, Integer pagina);

	public abstract Long buscarTotalEntregasParaEntregar(Integer codigoUsuario);

	public abstract List<EntregaDTO> buscarEntregasEntregues(Integer codigoUsuario, Integer pagina);

	public abstract Long buscarTotalEntregasEntregues(Integer codigoUsuario);

	public abstract List<EntregaDTO> buscarEntregasFinalizadasAdmin(Integer codigoUsuario, Integer pagina);

	public abstract Long buscarTotalEntregasFinalizadasAdmin(Integer codigoUsuario);

	public abstract List<EntregaDTO> buscarEntregasAdminAndamento(Integer codigoUsuario, Integer pagina);

	public abstract Long buscarTotalEntregasAdminAndamento(Integer codigoUsuario);

	public abstract List<EntregaDTO> buscarEntregasAbertas(Integer codigoUsuario, Integer tipoUsuario, Integer pagina);

	public abstract Long buscarTotalEntregasAbertas(Integer codigoUsuario, Integer tipoUsuario);

	public abstract List<EntregaDTO> buscarEntregasAndamento(Integer codigoUsuario, Integer tipoUsuario,
			Integer pagina);

	public abstract Long buscarTotalEntregasAndamento(Integer codigoUsuario, Integer tipoUsuario);

	public abstract List<EntregaDTO> buscarEntregasFinalizadas(Integer codigoUsuario, Integer tipoUsuario,
			Integer pagina);

	public abstract Long buscarTotalEntregasFinalizadas(Integer codigoUsuario, Integer tipoUsuario);

}
