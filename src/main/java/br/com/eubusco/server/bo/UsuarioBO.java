package br.com.eubusco.server.bo;

import java.util.List;

import br.com.eubusco.server.dto.LoginDTO;
import br.com.eubusco.server.dto.NovoUsuarioDTO;
import br.com.eubusco.server.dto.RetornoEfetuarLoginDTO;
import br.com.eubusco.server.model.Usuario;

public interface UsuarioBO {

	public abstract RetornoEfetuarLoginDTO efetuarLogin(LoginDTO loginDTO);

	public abstract String buscarNomeUsuario(Integer codigoUsuario);

	public abstract Usuario buscarPorCodigo(Integer codigoUsuario);

	public abstract Integer novoUsuario(NovoUsuarioDTO novoUsuarioDTO);

	public abstract List<Usuario> buscarTodosUsuarios();

}
