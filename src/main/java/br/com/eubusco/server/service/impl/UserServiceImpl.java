package br.com.eubusco.server.service.impl;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.eubusco.server.bo.UserBO;
import br.com.eubusco.server.dto.DadosUsuarioDTO;
import br.com.eubusco.server.dto.LoginDTO;
import br.com.eubusco.server.dto.NovoUsuarioDTO;
import br.com.eubusco.server.dto.ReturnLoginDTO;
import br.com.eubusco.server.model.Usuario;
import br.com.eubusco.server.service.UserService;

@ManagedBean
public class UserServiceImpl implements UserService {

	@Autowired
	private UserBO userBO;

	@Override
	public ReturnLoginDTO login(@RequestBody @Valid LoginDTO loginDTO) {
		return userBO.login(loginDTO);
	}

	@Override
	public Integer novoUsuario(@RequestBody @Valid NovoUsuarioDTO novoUsuarioDTO) {
		return userBO.novoUsuario(novoUsuarioDTO);
	}

	@Override
	public DadosUsuarioDTO buscarDadosUsuario(Integer idUsuario) {
		return userBO.buscarDadosUsuario(idUsuario);
	}

	@Override
	public List<Usuario> buscarTodosUsuarios() {
		return userBO.buscarTodosUsuarios();
	}

}
