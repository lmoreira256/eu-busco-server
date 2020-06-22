package br.com.eubusco.server.bo;

import java.util.List;

import br.com.eubusco.server.model.Contato;

public interface ContactBO {

	public abstract List<Contato> getFromUser(Integer userCode);

}
