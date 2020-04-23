package br.com.eubusco.server.service.impl;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eubusco.server.bo.UsuarioBO;
import br.com.eubusco.server.dto.DadosUsuarioDTO;
import br.com.eubusco.server.dto.LoginDTO;
import br.com.eubusco.server.dto.NovoUsuarioDTO;
import br.com.eubusco.server.dto.RetornoEfetuarLoginDTO;
import br.com.eubusco.server.model.Usuario;
import br.com.eubusco.server.service.UsuarioService;

@ManagedBean
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioBO usuarioBO;

	@Override
	public RetornoEfetuarLoginDTO efetuarLogin(@RequestBody @Valid LoginDTO loginDTO) {
		return usuarioBO.efetuarLogin(loginDTO);
	}

	@Override
	public Integer novoUsuario(@RequestBody @Valid NovoUsuarioDTO novoUsuarioDTO) {
		return usuarioBO.novoUsuario(novoUsuarioDTO);
	}

	@Override
	public DadosUsuarioDTO buscarDadosUsuario(Integer idUsuario) {
		return usuarioBO.buscarDadosUsuario(idUsuario);
	}

	@Override
	public List<Usuario> buscarTodosUsuarios() {
		return usuarioBO.buscarTodosUsuarios();
	}

}
