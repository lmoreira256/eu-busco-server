package br.com.eubusco.server.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.eubusco.server.dao.EstadoDAO;
import br.com.eubusco.server.model.Estado;

@Repository
public class EstadoDAOImpl extends GenericDAOImpl<Estado> implements EstadoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@PostConstruct
	public void inicializar() {
		setEntityManager(entityManager);
	}

}
