package br.com.eubusco.server.bo.impl;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.AvaliacaoBO;
import br.com.eubusco.server.constantes.MensagemService;
import br.com.eubusco.server.dao.AvaliacaoDAO;
import br.com.eubusco.server.model.Avaliacao;
import br.com.eubusco.server.resources.Resource;

@ManagedBean
public class AvaliacaoBOImpl implements AvaliacaoBO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AvaliacaoDAO avaliacaoDAO;

	@Override
	public Boolean salvar(Avaliacao avaliacao) {
		logger.info("==> Executando o método salvar.");

		if (avaliacao == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		Avaliacao retorno = avaliacaoDAO.salvar(avaliacao);

		return retorno != null;
	}

}