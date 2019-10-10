package br.com.eubusco.server.dao;

import java.util.List;

import br.com.eubusco.server.model.Endereco;

public interface EnderecoDAO extends GenericDAO<Endereco> {

	public abstract List<Endereco> adquirirTodosUsuario(Integer idUsuario);

}
