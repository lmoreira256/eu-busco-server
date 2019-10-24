package br.com.eubusco.server.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.eubusco.server.dao.TipoContatoDAO;
import br.com.eubusco.server.model.TipoContato;

@Repository
public class TipoContatoDAOImpl extends GenericDAOImpl<TipoContato> implements TipoContatoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@PostConstruct
	public void inicializar() {
		setEntityManager(entityManager);
	}

}
