package br.com.eubusco.server.dao.impl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.eubusco.server.dao.TipoUsuarioDAO;
import br.com.eubusco.server.model.TipoUsuario;

@Repository
public class TipoUsuarioDAOImpl extends GenericDAOImpl<TipoUsuario> implements TipoUsuarioDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@PostConstruct
	public void inicializar() {
		setEntityManager(entityManager);
	}

}
