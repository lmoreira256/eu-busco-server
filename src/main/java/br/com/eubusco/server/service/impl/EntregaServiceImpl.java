package br.com.eubusco.server.service.impl;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eubusco.server.bo.EntregaBO;
import br.com.eubusco.server.dto.ParametroPegarEntregaDTO;
import br.com.eubusco.server.dto.RetornoEntregaAvaliacaoDTO;
import br.com.eubusco.server.dto.RetornoEntregasDisponiveisDTO;
import br.com.eubusco.server.model.Entrega;
import br.com.eubusco.server.service.EntregaService;

@ManagedBean
public class EntregaServiceImpl implements EntregaService {

	@Autowired
	private EntregaBO entregaBO;

	@Override
	public Boolean salvar(@RequestBody @Valid Entrega entrega) {
		return entregaBO.salvar(entrega);
	}

	@Override
	public List<RetornoEntregasDisponiveisDTO> buscarAbertasCliente(Integer idUsuario) {
		return entregaBO.buscarAbertasCliente(idUsuario);
	}

	@Override
	public List<RetornoEntregasDisponiveisDTO> buscarAbertasEntregador(Integer idUsuario) {
		return entregaBO.buscarAbertasEntregador(idUsuario);
	}

	@Override
	public List<RetornoEntregasDisponiveisDTO> buscarDisponiveis() {
		return entregaBO.buscarDisponiveis();
	}

	@Override
	public Boolean pegarEntrega(@RequestBody @Valid ParametroPegarEntregaDTO parametroPegarEntregaDTO) {
		return entregaBO.pegarEntrega(parametroPegarEntregaDTO);
	}

	@Override
	public Boolean largarEntrega(@RequestBody @Valid Integer codigoEntrega) {
		return entregaBO.largarEntrega(codigoEntrega);
	}

	@Override
	public Boolean excluirEntrega(@RequestBody @Valid Integer codigoEntrega) {
		return entregaBO.excluirEntrega(codigoEntrega);
	}

	@Override
	public List<RetornoEntregasDisponiveisDTO> buscarTodasAbertas() {
		return entregaBO.buscarTodasAbertas();
	}

	@Override
	public Boolean finalizarEntrega(@RequestBody @Valid Integer codigoEntrega) {
		return entregaBO.finalizarEntrega(codigoEntrega);
	}

	@Override
	public List<RetornoEntregaAvaliacaoDTO> buscarEntregasAvaliacao(Integer codigoUsuario) {
		return entregaBO.buscarEntregasAvaliacao(codigoUsuario);
	}

}
