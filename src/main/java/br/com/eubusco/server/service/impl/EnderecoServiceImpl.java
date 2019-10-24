package br.com.eubusco.server.service.impl;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eubusco.server.bo.EnderecoBO;
import br.com.eubusco.server.model.Endereco;
import br.com.eubusco.server.service.EnderecoService;

@ManagedBean
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoBO enderecoBO;

	@Override
	public Boolean salvar(@RequestBody @Valid Endereco endereco) {
		return enderecoBO.salvar(endereco);
	}

	@Override
	public List<Endereco> adquirirTodosUsuario(Integer idUsuario) {
		return enderecoBO.adquirirTodosUsuario(idUsuario);
	}

}
