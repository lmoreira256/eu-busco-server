package br.com.eubusco.server.bo;

import java.util.List;

import br.com.eubusco.server.model.TipoContato;

public interface TipoContatoBO {

	public abstract List<TipoContato> adquirirTodos();

}
