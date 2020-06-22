package br.com.eubusco.server.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.EntregaBO;
import br.com.eubusco.server.bo.UsuarioBO;
import br.com.eubusco.server.constantes.MensagemService;
import br.com.eubusco.server.dao.EntregaDAO;
import br.com.eubusco.server.dto.PaginacaoDTO;
import br.com.eubusco.server.dto.ParametroPegarEntregaDTO;
import br.com.eubusco.server.dto.RetornoBuscarEntregasDTO;
import br.com.eubusco.server.dto.RetornoEntregaAvaliacaoDTO;
import br.com.eubusco.server.model.Entrega;
import br.com.eubusco.server.model.Usuario;
import br.com.eubusco.server.resources.Resource;

@ManagedBean
public class EntregaBOImpl implements EntregaBO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntregaDAO entregaDAO;

	@Autowired
	private UsuarioBO usuarioBO;

	@Override
	public Boolean salvar(Entrega entrega) {
		logger.info("==> Executando o método salvar.");

		if (entrega == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		entrega.setFlagFinalizada(false);
		entrega.setDataCadastro(new Date());
		entrega.setDataManutencao(new Date());

		Entrega retorno = entregaDAO.salvar(entrega);

		return retorno != null;
	}

	@Override
	public Boolean pegarEntrega(ParametroPegarEntregaDTO parametroPegarEntregaDTO) {
		logger.info("==> Executando o método pegarEntrega.");

		if (parametroPegarEntregaDTO == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		Entrega entrega = entregaDAO.buscarPorId(parametroPegarEntregaDTO.getCodigoEntrega());
		entrega.setCodigoEntregador(parametroPegarEntregaDTO.getCodigoEntregador());
		entrega.setDataManutencao(new Date());

		entregaDAO.salvar(entrega);

		return Boolean.TRUE;
	}

	@Override
	public Boolean largarEntrega(Integer codigoEntrega) {
		logger.info("==> Executando o método largarEntrega.");

		if (codigoEntrega == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		Entrega entrega = entregaDAO.buscarPorId(codigoEntrega);
		entrega.setCodigoEntregador(null);
		entrega.setDataManutencao(new Date());

		entregaDAO.salvar(entrega);

		return Boolean.TRUE;
	}

	@Override
	public Boolean excluirEntrega(Integer codigoEntrega) {
		logger.info("==> Executando o método excluirEntrega.");

		if (codigoEntrega == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		Entrega entrega = entregaDAO.buscarPorId(codigoEntrega);
		entrega.setDataManutencao(new Date());
		entrega.setDataExclusao(new Date());
		entrega.setFlagFinalizada(Boolean.TRUE);

		entregaDAO.salvar(entrega);

		return Boolean.TRUE;
	}

	@Override
	public Boolean finalizarEntrega(Integer codigoEntrega) {
		logger.info("==> Executando o método finalizarEntrega.");

		if (codigoEntrega == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		Entrega entrega = entregaDAO.buscarPorId(codigoEntrega);
		entrega.setDataManutencao(new Date());
		entrega.setFlagFinalizada(Boolean.TRUE);

		entregaDAO.salvar(entrega);

		return Boolean.TRUE;
	}

	@Override
	public List<RetornoEntregaAvaliacaoDTO> buscarEntregasAvaliacao(Integer codigoUsuario) {
		logger.info("==> Executando o método buscarEntregasAvaliacao.");

		Usuario usuario = usuarioBO.buscarPorCodigo(codigoUsuario);
		List<Entrega> entregas = entregaDAO.buscarEntregasAvaliacao(codigoUsuario, usuario.getCodigoTipoUsuario());

		List<RetornoEntregaAvaliacaoDTO> retorno = new ArrayList<RetornoEntregaAvaliacaoDTO>();

		entregas.stream().forEach(x -> {
			RetornoEntregaAvaliacaoDTO retornoEntregaAvaliacaoDTO = new RetornoEntregaAvaliacaoDTO();

			retornoEntregaAvaliacaoDTO.setCodigoEntrega(x.getId());
			retornoEntregaAvaliacaoDTO.setTituloEntrega(x.getTitulo());

			if (usuario.getCodigoTipoUsuario() == 2) {
				retornoEntregaAvaliacaoDTO.setNomeAvaliado(usuarioBO.buscarNomeUsuario(x.getCodigoEntregador()));
			} else {
				retornoEntregaAvaliacaoDTO.setNomeAvaliado(usuarioBO.buscarNomeUsuario(x.getCodigoCliente()));
			}

			retorno.add(retornoEntregaAvaliacaoDTO);
		});

		return retorno;
	}

	@Override
	public RetornoBuscarEntregasDTO buscarEntregas(Integer codigoUsuario) {
		logger.info("*** Rodando o método buscarEntregas ***");

		Integer tipoUsuario = usuarioBO.buscarTipoUsuario(codigoUsuario);

		PaginacaoDTO abertas = buscarEntregasAbertas(codigoUsuario, tipoUsuario, 1);
		PaginacaoDTO andamento = buscarEntregasAndamento(codigoUsuario, tipoUsuario, 1);
		PaginacaoDTO finalizadas = buscarEntregasFinalizadas(codigoUsuario, tipoUsuario, 1);
		PaginacaoDTO excluidas = buscarEntregasExcluidas(codigoUsuario, tipoUsuario, 1);

		return new RetornoBuscarEntregasDTO(abertas, andamento, finalizadas, excluidas);
	}

	@Override
	public PaginacaoDTO buscarEntregasAbertas(Integer codigoUsuario, Integer tipoUsuario, Integer pagina) {
		logger.info("*** Rodando o método buscarEntregasAbertas ***");

		PaginacaoDTO paginacaoDTO = new PaginacaoDTO();
		paginacaoDTO.setLista(entregaDAO.buscarEntregasAbertas(codigoUsuario, tipoUsuario, pagina));
		paginacaoDTO.setTotal(entregaDAO.buscarTotalEntregasAbertas(codigoUsuario, tipoUsuario));

		return paginacaoDTO;
	}

	@Override
	public PaginacaoDTO buscarEntregasAndamento(Integer codigoUsuario, Integer tipoUsuario, Integer pagina) {
		logger.info("*** Rodando o método buscarEntregasAndamento ***");

		PaginacaoDTO paginacaoDTO = new PaginacaoDTO();
		paginacaoDTO.setLista(entregaDAO.buscarEntregasAndamento(codigoUsuario, tipoUsuario, pagina));
		paginacaoDTO.setTotal(entregaDAO.buscarTotalEntregasAndamento(codigoUsuario, tipoUsuario));

		return paginacaoDTO;
	}

	@Override
	public PaginacaoDTO buscarEntregasFinalizadas(Integer codigoUsuario, Integer tipoUsuario, Integer pagina) {
		logger.info("*** Rodando o método buscarEntregasFinalizadas ***");

		PaginacaoDTO paginacaoDTO = new PaginacaoDTO();
		paginacaoDTO.setLista(entregaDAO.buscarEntregasFinalizadas(codigoUsuario, tipoUsuario, pagina));
		paginacaoDTO.setTotal(entregaDAO.buscarTotalEntregasFinalizadas(codigoUsuario, tipoUsuario));

		return paginacaoDTO;
	}

	@Override
	public PaginacaoDTO buscarEntregasExcluidas(Integer codigoUsuario, Integer tipoUsuario, Integer pagina) {
		logger.info("*** Rodando o método buscarEntregasExcluidas ***");

		PaginacaoDTO paginacaoDTO = new PaginacaoDTO();
		paginacaoDTO.setLista(entregaDAO.buscarEntregasExcluidas(codigoUsuario, tipoUsuario, pagina));
		paginacaoDTO.setTotal(entregaDAO.buscarTotalEntregasExcluidas(codigoUsuario, tipoUsuario));

		return paginacaoDTO;
	}

}
