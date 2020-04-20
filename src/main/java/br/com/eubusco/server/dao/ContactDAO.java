package br.com.eubusco.server.dao;

import java.util.List;

import br.com.eubusco.server.model.Contato;

public interface ContactDAO extends GenericDAO<Contato> {

	public abstract List<Contato> getFromUser(Integer userCode);

}
