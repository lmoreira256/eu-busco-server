package br.com.eubusco.server.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.EntregaBO;
import br.com.eubusco.server.constantes.MensagemService;
import br.com.eubusco.server.dao.ContatoDAO;
import br.com.eubusco.server.dao.EnderecoDAO;
import br.com.eubusco.server.dao.EntregaDAO;
import br.com.eubusco.server.dao.UsuarioDAO;
import br.com.eubusco.server.dto.ParametroPegarEntregaDTO;
import br.com.eubusco.server.dto.RetornoEntregasDisponiveisDTO;
import br.com.eubusco.server.model.Entrega;
import br.com.eubusco.server.resources.Resource;

@ManagedBean
public class EntregaBOImpl implements EntregaBO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EntregaDAO entregaDAO;

	@Autowired
	private EnderecoDAO enderecoDAO;

	@Autowired
	private UsuarioDAO usuarioDAO;

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
				entrega.setNomeCliente(usuarioDAO.buscarNomePorId(x.getCodigoCliente()));
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

}
