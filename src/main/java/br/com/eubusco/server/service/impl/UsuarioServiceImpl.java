package br.com.eubusco.server.service.impl;

import javax.annotation.ManagedBean;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eubusco.server.bo.UsuarioBO;
import br.com.eubusco.server.dto.DadosUsuarioDTO;
import br.com.eubusco.server.dto.LoginDTO;
import br.com.eubusco.server.dto.NovoUsuarioDTO;
import br.com.eubusco.server.dto.RetornoLoginDTO;
import br.com.eubusco.server.service.UsuarioService;

@ManagedBean
public class UsuarioServiceImpl implements UsuarioService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UsuarioBO usuarioBO;

	@Override
	public RetornoLoginDTO realizarLogin(@RequestBody @Valid LoginDTO loginDTO) {
		logger.info("==> Executando o método realizarLogin.");

		return usuarioBO.realizarLogin(loginDTO);
	}

	@Override
	public Integer novoUsuario(@RequestBody @Valid NovoUsuarioDTO novoUsuarioDTO) {
		logger.info("==> Executando o método novoUsuario.");

		return usuarioBO.novoUsuario(novoUsuarioDTO);
	}

	@Override
	public DadosUsuarioDTO buscarDadosUsuario(Integer idUsuario) {
		logger.info("==> Executando o método buscarDadosUsuario.");

		return usuarioBO.buscarDadosUsuario(idUsuario);
	}

}
