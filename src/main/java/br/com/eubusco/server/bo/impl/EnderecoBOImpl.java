package br.com.eubusco.server.bo.impl;

import java.util.List;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eubusco.server.bo.EnderecoBO;
import br.com.eubusco.server.constantes.MensagemService;
import br.com.eubusco.server.dao.EnderecoDAO;
import br.com.eubusco.server.model.Endereco;
import br.com.eubusco.server.resources.Resource;

@ManagedBean
public class EnderecoBOImpl implements EnderecoBO {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EnderecoDAO enderecoDAO;

	@Override
	public Boolean salvar(Endereco endereco) {
		logger.info("==> Executando o método salvar.");

		if (endereco == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		Endereco retorno = enderecoDAO.salvar(endereco);

		return retorno != null;
	}

	@Override
	public List<Endereco> adquirirTodosUsuario(Integer idUsuario) {
		logger.info("==> Executando o método adquirirTodosUsuario.");

		if (idUsuario == null) {
			throw Resource.getServerException(MensagemService.PARAMETRO_NULO);
		}

		return enderecoDAO.adquirirTodosUsuario(idUsuario);
	}

}
