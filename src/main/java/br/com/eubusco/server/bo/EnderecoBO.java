package br.com.eubusco.server.bo;

import java.util.List;

import br.com.eubusco.server.model.Endereco;

public interface EnderecoBO {

	public abstract Boolean salvar(Endereco endereco);

	public abstract List<Endereco> adquirirTodosUsuario(Integer idUsuario);

}
