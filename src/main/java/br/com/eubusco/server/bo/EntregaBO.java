package br.com.eubusco.server.bo;

import java.util.List;

import br.com.eubusco.server.dto.PaginacaoDTO;
import br.com.eubusco.server.dto.ParametroPegarEntregaDTO;
import br.com.eubusco.server.dto.RetornoBuscarEntregasDTO;
import br.com.eubusco.server.dto.RetornoEntregaAvaliacaoDTO;
import br.com.eubusco.server.model.Entrega;

public interface EntregaBO {

	public abstract Boolean salvar(Entrega entrega);

	public abstract Boolean pegarEntrega(ParametroPegarEntregaDTO parametroPegarEntregaDTO);

	public abstract Boolean largarEntrega(Integer codigoEntrega);

	public abstract Boolean excluirEntrega(Integer codigoEntrega);

	public abstract Boolean finalizarEntrega(Integer codigoEntrega);

	public abstract List<RetornoEntregaAvaliacaoDTO> buscarEntregasAvaliacao(Integer codigoUsuario);

	public abstract RetornoBuscarEntregasDTO buscarEntregas(Integer codigoUsuario);

	public abstract PaginacaoDTO buscarEntregasAbertas(Integer codigoUsuario, Integer tipoUsuario, Integer pagina);

	public abstract PaginacaoDTO buscarEntregasAndamento(Integer codigoUsuario, Integer tipoUsuario, Integer pagina);

	public abstract PaginacaoDTO buscarEntregasFinalizadas(Integer codigoUsuario, Integer tipoUsuario, Integer pagina);

	public abstract PaginacaoDTO buscarEntregasExcluidas(Integer codigoUsuario, Integer tipoUsuario, Integer pagina);

}
