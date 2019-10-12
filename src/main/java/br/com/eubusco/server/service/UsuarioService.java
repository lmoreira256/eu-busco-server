package br.com.eubusco.server.service;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.eubusco.server.dto.DadosUsuarioDTO;
import br.com.eubusco.server.dto.LoginDTO;
import br.com.eubusco.server.dto.NovoUsuarioDTO;
import br.com.eubusco.server.dto.RetornoLoginDTO;

@RestController
@RequestMapping("usuarioService")
public interface UsuarioService {

	@CrossOrigin
	@RequestMapping(path = "realizarLogin", method = RequestMethod.POST, produces = "application/json")
	public abstract RetornoLoginDTO realizarLogin(LoginDTO loginDTO);

	@RequestMapping(path = "novoUsuario", method = RequestMethod.POST, produces = "application/json")
	public abstract Integer novoUsuario(NovoUsuarioDTO novoUsuarioDTO);

	@RequestMapping(path = "buscarDadosUsuario", method = RequestMethod.GET, produces = "application/json")
	public abstract DadosUsuarioDTO buscarDadosUsuario(Integer idUsuario);

}
