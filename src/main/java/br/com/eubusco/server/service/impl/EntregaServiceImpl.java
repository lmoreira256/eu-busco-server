package br.com.eubusco.server.service.impl;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eubusco.server.bo.EntregaBO;
import br.com.eubusco.server.dto.PaginacaoDTO;
import br.com.eubusco.server.dto.ParametroPegarEntregaDTO;
import br.com.eubusco.server.dto.RetornoBuscarEntregasDTO;
import br.com.eubusco.server.dto.RetornoEntregaAvaliacaoDTO;
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
	public Boolean finalizarEntrega(@RequestBody @Valid Integer codigoEntrega) {
		return entregaBO.finalizarEntrega(codigoEntrega);
	}

	@Override
	public List<RetornoEntregaAvaliacaoDTO> buscarEntregasAvaliacao(Integer codigoUsuario) {
		return entregaBO.buscarEntregasAvaliacao(codigoUsuario);
	}

	@Override
	public PaginacaoDTO buscarEntregasAbertas(Integer codigoUsuario, Integer tipoUsuario, Integer pagina) {
		return entregaBO.buscarEntregasAbertas(codigoUsuario, tipoUsuario, pagina);
	}

	@Override
	public PaginacaoDTO buscarEntregasAndamento(Integer codigoUsuario, Integer tipoUsuario, Integer pagina) {
		return entregaBO.buscarEntregasAndamento(codigoUsuario, tipoUsuario, pagina);
	}

	@Override
	public PaginacaoDTO buscarEntregasFinalizadas(Integer codigoUsuario, Integer tipoUsuario, Integer pagina) {
		return entregaBO.buscarEntregasFinalizadas(codigoUsuario, tipoUsuario, pagina);
	}

	@Override
	public PaginacaoDTO buscarEntregasExcluidas(Integer codigoUsuario, Integer tipoUsuario, Integer pagina) {
		return entregaBO.buscarEntregasExcluidas(codigoUsuario, tipoUsuario, pagina);
	}

	@Override
	public RetornoBuscarEntregasDTO buscarEntregas(Integer codigoUsuario) {
		return entregaBO.buscarEntregas(codigoUsuario);
	}

}
