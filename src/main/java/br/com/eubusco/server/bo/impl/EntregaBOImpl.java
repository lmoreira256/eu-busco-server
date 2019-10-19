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
import br.com.eubusco.server.dao.EnderecoDAO;
import br.com.eubusco.server.dao.EntregaDAO;
import br.com.eubusco.server.dao.UsuarioDAO;
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
	public List<Entrega> buscarAbertasCliente(Integer idUsuario) {
		logger.info("==> Executando o método buscarAbertasCliente.");

		if (idUsuario == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		return entregaDAO.buscarAbertasCliente(idUsuario);
	}

	@Override
	public List<Entrega> buscarAbertasEntregador(Integer idUsuario) {
		logger.info("==> Executando o método buscarAbertasEntregador.");

		if (idUsuario == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		return entregaDAO.buscarAbertasEntregador(idUsuario);
	}

	@Override
	public List<RetornoEntregasDisponiveisDTO> buscarDisponiveis() {
		logger.info("==> Executando o método buscarDisponiveis.");

		List<Entrega> entregaList = entregaDAO.buscarDisponiveis();
		List<RetornoEntregasDisponiveisDTO> retorno = new ArrayList<RetornoEntregasDisponiveisDTO>();

		if (entregaList != null) {
			entregaList.stream().forEach(x -> {
				RetornoEntregasDisponiveisDTO entrega = new RetornoEntregasDisponiveisDTO();
				entrega.setDataColetaEntrega(x.getDataColeta());
				entrega.setDataPrazoEntrega(x.getDataPrazoEntrega());
				entrega.setDescricaoEntrega(x.getDescricao());
				entrega.setEnderecoColeta(enderecoDAO.buscarPorId(x.getCodigoEnderecoColeta()));
				entrega.setEnderecoEntrega(enderecoDAO.buscarPorId(x.getCodigoEnderecoEntrega()));
				entrega.setNomeCliente(usuarioDAO.buscarNomePorId(x.getCodigoCliente()));
				entrega.setTituloEntrega(x.getTitulo());
				entrega.setVolumeEntrega(x.getVolume());

				retorno.add(entrega);
			});
		}

		return retorno;
	}

}
