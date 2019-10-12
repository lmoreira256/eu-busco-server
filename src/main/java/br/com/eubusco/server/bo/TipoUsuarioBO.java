package br.com.eubusco.server.bo;

import java.util.List;

import br.com.eubusco.server.model.TipoUsuario;

public interface TipoUsuarioBO {

	public abstract List<TipoUsuario> adquirirTodos();

}
