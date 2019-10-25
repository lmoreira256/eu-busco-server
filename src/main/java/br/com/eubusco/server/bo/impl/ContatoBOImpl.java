package br.com.eubusco.server.bo.impl;

import java.util.List;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.ContatoBO;
import br.com.eubusco.server.constantes.MensagemService;
import br.com.eubusco.server.dao.ContatoDAO;
import br.com.eubusco.server.model.Contato;
import br.com.eubusco.server.resources.Resource;

@ManagedBean
public class ContatoBOImpl implements ContatoBO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ContatoDAO contatoDAO;

	@Override
	public List<Contato> adquirirPorUsuario(Integer codigoUsuario) {
		logger.info("==> Executando o método adquirirPorUsuario.");

		return contatoDAO.adquirirPorUsuario(codigoUsuario);
	}

	@Override
	public Boolean salvarContato(Contato contato) {
		logger.info("==> Executando o método salvarContato.");

		if (contato == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		Contato retorno = contatoDAO.salvar(contato);

		return retorno != null;
	}

}
