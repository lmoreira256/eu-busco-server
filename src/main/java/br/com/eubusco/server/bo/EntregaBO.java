package br.com.eubusco.server.bo;

import java.util.List;

import br.com.eubusco.server.dto.ParametroPegarEntregaDTO;
import br.com.eubusco.server.dto.RetornoEntregasDisponiveisDTO;
import br.com.eubusco.server.model.Entrega;

public interface EntregaBO {

	public abstract Boolean salvar(Entrega entrega);

	public abstract List<RetornoEntregasDisponiveisDTO> buscarAbertasCliente(Integer idUsuario);

	public abstract List<RetornoEntregasDisponiveisDTO> buscarAbertasEntregador(Integer idUsuario);

	public abstract List<RetornoEntregasDisponiveisDTO> buscarDisponiveis();

	public abstract Boolean pegarEntrega(ParametroPegarEntregaDTO parametroPegarEntregaDTO);

}
