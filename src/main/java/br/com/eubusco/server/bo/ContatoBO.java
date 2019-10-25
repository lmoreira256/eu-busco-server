package br.com.eubusco.server.bo;

import java.util.List;

import br.com.eubusco.server.model.Contato;

public interface ContatoBO {

	public abstract List<Contato> adquirirPorUsuario(Integer codigoUsuario);

	public abstract Boolean salvarContato(Contato contato);

}
