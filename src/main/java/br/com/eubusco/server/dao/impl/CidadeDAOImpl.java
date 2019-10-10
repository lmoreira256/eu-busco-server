package br.com.eubusco.server.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.eubusco.server.dao.CidadeDAO;
import br.com.eubusco.server.model.Cidade;

@Repository
public class CidadeDAOImpl extends GenericDAOImpl<Cidade> implements CidadeDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@PostConstruct
	public void inicializar() {
		setEntityManager(entityManager);
	}

}
