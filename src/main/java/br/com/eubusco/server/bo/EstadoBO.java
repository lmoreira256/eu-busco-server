package br.com.eubusco.server.bo;

import java.util.List;

import br.com.eubusco.server.model.Estado;

public interface EstadoBO {

	public abstract List<Estado> adquirirTodos();

}
