package br.com.eubusco.server.service.impl;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eubusco.server.bo.EnderecoBO;
import br.com.eubusco.server.model.Endereco;
import br.com.eubusco.server.service.EnderecoService;

@ManagedBean
public class EnderecoServiceImpl implements EnderecoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EnderecoBO enderecoBO;

	@Override
	public Boolean salvar(@RequestBody @Valid Endereco endereco) {
		logger.info("==> Executando o método salvar.");

		return enderecoBO.salvar(endereco);
	}

	@Override
	public List<Endereco> adquirirTodosUsuario(Integer idUsuario) {
		logger.info("==> Executando o método adquirirTodosUsuario.");

		return enderecoBO.adquirirTodosUsuario(idUsuario);
	}

}
