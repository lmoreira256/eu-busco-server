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
import br.com.eubusco.server.dao.ContatoDAO;
import br.com.eubusco.server.dao.EnderecoDAO;
import br.com.eubusco.server.dao.EntregaDAO;
import br.com.eubusco.server.dto.PaginacaoDTO;
import br.com.eubusco.server.dto.ParametroPegarEntregaDTO;
import br.com.eubusco.server.dto.RetornoEntregaAvaliacaoDTO;
import br.com.eubusco.server.dto.RetornoEntregasDisponiveisDTO;
import br.com.eubusco.server.model.Entrega;
import br.com.eubusco.server.model.Usuario;
import br.com.eubusco.server.resources.Resource;

@ManagedBean
public class EntregaBOImpl implements EntregaBO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntregaDAO entregaDAO;

	@Autowired
	private EnderecoDAO enderecoDAO;

	@Autowired
	private UsuarioBO usuarioBO;

	@Autowired
	private ContatoDAO contatoDAO;

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
	public List<RetornoEntregasDisponiveisDTO> buscarAbertasCliente(Integer idUsuario) {
		logger.info("==> Executando o método buscarAbertasCliente.");

		if (idUsuario == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		return this.montarDTO(entregaDAO.buscarAbertasCliente(idUsuario));
	}

	@Override
	public List<RetornoEntregasDisponiveisDTO> buscarAbertasEntregador(Integer idUsuario) {
		logger.info("==> Executando o método buscarAbertasEntregador.");

		if (idUsuario == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		return this.montarDTO(entregaDAO.buscarAbertasEntregador(idUsuario));
	}

	@Override
	public List<RetornoEntregasDisponiveisDTO> buscarDisponiveis() {
		logger.info("==> Executando o método buscarDisponiveis.");

		return this.montarDTO(entregaDAO.buscarDisponiveis());
	}

	private List<RetornoEntregasDisponiveisDTO> montarDTO(List<Entrega> listaEntregas) {
		List<RetornoEntregasDisponiveisDTO> retorno = new ArrayList<RetornoEntregasDisponiveisDTO>();

		if (listaEntregas != null) {
			listaEntregas.stream().forEach(x -> {
				RetornoEntregasDisponiveisDTO entrega = new RetornoEntregasDisponiveisDTO();
				entrega.setIdEntrega(x.getId());
				entrega.setDataColetaEntrega(x.getDataColeta());
				entrega.setDataPrazoEntrega(x.getDataPrazoEntrega());
				entrega.setDescricaoEntrega(x.getDescricao());
				entrega.setEnderecoColeta(enderecoDAO.buscarPorId(x.getCodigoEnderecoColeta()));
				entrega.setEnderecoEntrega(enderecoDAO.buscarPorId(x.getCodigoEnderecoEntrega()));
				entrega.setNomeCliente(usuarioBO.buscarNomeUsuario(x.getCodigoCliente()));
				entrega.setTituloEntrega(x.getTitulo());
				entrega.setVolumeEntrega(x.getVolume());
				entrega.setContatosCliente(contatoDAO.adquirirPorUsuario(x.getCodigoCliente()));

				retorno.add(entrega);
			});
		}

		return retorno;
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

		entregaDAO.salvar(entrega);

		return Boolean.TRUE;
	}

	@Override
	public List<RetornoEntregasDisponiveisDTO> buscarTodasAbertas() {
		return this.montarDTO(entregaDAO.buscarTodasAbertas());
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
	public PaginacaoDTO buscarEntregasUsuarioAbertas(Integer codigoUsuario, Integer pagina) {
		logger.info("*** Rodando o método buscarEntregasUsuarioAbertas ***");

		PaginacaoDTO paginacaoDTO = new PaginacaoDTO();
		paginacaoDTO.setLista(entregaDAO.buscarEntregasUsuarioAbertas(codigoUsuario, pagina));
		paginacaoDTO.setTotal(entregaDAO.buscarTotalEntregasUsuarioAbertas(codigoUsuario));

		return paginacaoDTO;
	}

	@Override
	public PaginacaoDTO buscarEntregasUsuarioAndamento(Integer codigoUsuario, Integer pagina) {
		logger.info("*** Rodando o método buscarEntregasUsuarioAndamento ***");

		PaginacaoDTO paginacaoDTO = new PaginacaoDTO();
		paginacaoDTO.setLista(entregaDAO.buscarEntregasUsuarioAndamento(codigoUsuario, pagina));
		paginacaoDTO.setTotal(entregaDAO.buscarTotalEntregasUsuarioAndamento(codigoUsuario));

		return paginacaoDTO;
	}

	@Override
	public PaginacaoDTO buscarEntregasAbertas(Integer codigoUsuario, Integer pagina) {
		logger.info("*** Rodando o método buscarEntregasAbertas ***");

		PaginacaoDTO paginacaoDTO = new PaginacaoDTO();
		paginacaoDTO.setLista(entregaDAO.buscarEntregasAbertas(codigoUsuario, pagina));
		paginacaoDTO.setTotal(entregaDAO.buscarTotalEntregasAbertas(codigoUsuario));

		return paginacaoDTO;
	}

	@Override
	public PaginacaoDTO buscarEntregasParaEntregar(Integer codigoUsuario, Integer pagina) {
		logger.info("*** Rodando o método buscarEntregasParaEntregar ***");

		PaginacaoDTO paginacaoDTO = new PaginacaoDTO();
		paginacaoDTO.setLista(entregaDAO.buscarEntregasParaEntregar(codigoUsuario, pagina));
		paginacaoDTO.setTotal(entregaDAO.buscarTotalEntregasParaEntregar(codigoUsuario));

		return paginacaoDTO;
	}

	@Override
	public PaginacaoDTO buscarEntregasEntregues(Integer codigoUsuario, Integer pagina) {
		logger.info("*** Rodando o método buscarEntregasEntregues ***");

		PaginacaoDTO paginacaoDTO = new PaginacaoDTO();
		paginacaoDTO.setLista(entregaDAO.buscarEntregasEntregues(codigoUsuario, pagina));
		paginacaoDTO.setTotal(entregaDAO.buscarTotalEntregasEntregues(codigoUsuario));

		return paginacaoDTO;
	}

	@Override
	public PaginacaoDTO buscarEntregasFinalizadas(Integer codigoUsuario, Integer pagina) {
		logger.info("*** Rodando o método buscarEntregasFinalizadas ***");

		PaginacaoDTO paginacaoDTO = new PaginacaoDTO();
		paginacaoDTO.setLista(entregaDAO.buscarEntregasFinalizadas(codigoUsuario, pagina));
		paginacaoDTO.setTotal(entregaDAO.buscarTotalEntregasFinalizadas(codigoUsuario));

		return paginacaoDTO;
	}

	@Override
	public PaginacaoDTO buscarEntregasFinalizadasAdmin(Integer codigoUsuario, Integer pagina) {
		logger.info("*** Rodando o método buscarEntregasFinalizadasAdmin ***");

		PaginacaoDTO paginacaoDTO = new PaginacaoDTO();
		paginacaoDTO.setLista(entregaDAO.buscarEntregasFinalizadasAdmin(codigoUsuario, pagina));
		paginacaoDTO.setTotal(entregaDAO.buscarTotalEntregasFinalizadasAdmin(codigoUsuario));

		return paginacaoDTO;
	}

	@Override
	public PaginacaoDTO buscarEntregasAdminAndamento(Integer codigoUsuario, Integer pagina) {
		logger.info("*** Rodando o método buscarEntregasAdminAndamento ***");

		PaginacaoDTO paginacaoDTO = new PaginacaoDTO();
		paginacaoDTO.setLista(entregaDAO.buscarEntregasAdminAndamento(codigoUsuario, pagina));
		paginacaoDTO.setTotal(entregaDAO.buscarTotalEntregasAdminAndamento(codigoUsuario));

		return paginacaoDTO;
	}

}
