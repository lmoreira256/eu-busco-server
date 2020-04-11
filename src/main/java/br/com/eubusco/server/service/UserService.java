package br.com.eubusco.server.service;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.eubusco.server.dto.DadosUsuarioDTO;
import br.com.eubusco.server.dto.LoginDTO;
import br.com.eubusco.server.dto.NovoUsuarioDTO;
import br.com.eubusco.server.dto.ReturnLoginDTO;
import br.com.eubusco.server.model.Usuario;

@RestController
@RequestMapping("userService")
public interface UserService {

	@CrossOrigin
	@RequestMapping(path = "login", method = RequestMethod.POST, produces = "application/json")
	public abstract ReturnLoginDTO login(LoginDTO loginDTO);

	@CrossOrigin
	@RequestMapping(path = "novoUsuario", method = RequestMethod.POST, produces = "application/json")
	public abstract Integer novoUsuario(NovoUsuarioDTO novoUsuarioDTO);

	@CrossOrigin
	@RequestMapping(path = "buscarDadosUsuario", method = RequestMethod.GET, produces = "application/json")
	public abstract DadosUsuarioDTO buscarDadosUsuario(Integer idUsuario);

	@CrossOrigin
	@RequestMapping(path = "buscarTodosUsuarios", method = RequestMethod.GET, produces = "application/json")
	public abstract List<Usuario> buscarTodosUsuarios();

}