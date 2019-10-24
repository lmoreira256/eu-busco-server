package br.com.eubusco.server.bo;

import java.util.List;

import br.com.eubusco.server.dto.DadosUsuarioDTO;
import br.com.eubusco.server.dto.LoginDTO;
import br.com.eubusco.server.dto.NovoUsuarioDTO;
import br.com.eubusco.server.dto.RetornoLoginDTO;
import br.com.eubusco.server.model.Usuario;

public interface UsuarioBO {

	public abstract RetornoLoginDTO realizarLogin(LoginDTO loginDTO);

	public abstract Integer novoUsuario(NovoUsuarioDTO novoUsuarioDTO);

	public abstract DadosUsuarioDTO buscarDadosUsuario(Integer idUsuario);

	public abstract List<Usuario> buscarTodosUsuarios();

}
