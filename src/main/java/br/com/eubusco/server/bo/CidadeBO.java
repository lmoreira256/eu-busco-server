package br.com.eubusco.server.bo;

import java.util.List;

import br.com.eubusco.server.model.Cidade;

public interface CidadeBO {

	public abstract List<Cidade> adquirirTodos();

}
