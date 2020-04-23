package br.com.eubusco.server.bo.impl;

import java.math.BigDecimal;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.AvaliacaoBO;
import br.com.eubusco.server.bo.UsuarioBO;
import br.com.eubusco.server.constantes.MensagemService;
import br.com.eubusco.server.dao.AvaliacaoDAO;
import br.com.eubusco.server.dao.EntregaDAO;
import br.com.eubusco.server.model.Avaliacao;
import br.com.eubusco.server.model.Usuario;
import br.com.eubusco.server.resources.Resource;

@ManagedBean
public class AvaliacaoBOImpl implements AvaliacaoBO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AvaliacaoDAO avaliacaoDAO;

	@Autowired
	private UsuarioBO usuarioBO;

	@Autowired
	private EntregaDAO entregaDAO;

	@Override
	public Boolean salvar(Avaliacao avaliacao) {
		logger.info("==> Executando o método salvar.");

		if (avaliacao == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		Usuario usuario = usuarioBO.buscarPorCodigo(avaliacao.getCodigoCliente());
		Boolean usuarioCliente = usuario.getCodigoTipoUsuario() == 2;

		avaliacao.setCodigoTipoAvaliacao(usuarioCliente ? 1 : 2);
		avaliacao.setCodigoEntregador(entregaDAO.buscarCodigoEntregador(avaliacao.getCodigoEntrega()));
		avaliacao.setCodigoCliente(entregaDAO.buscarCodigoCliente(avaliacao.getCodigoEntrega()));

		avaliacao = avaliacaoDAO.salvar(avaliacao);

		return avaliacao != null;
	}

	@Override
	public BigDecimal buscarNotaUsuario(Integer codigoUsuario, Integer tipoUsuario) {
		return avaliacaoDAO.buscarNotaUsuario(codigoUsuario, tipoUsuario);
	}

}
