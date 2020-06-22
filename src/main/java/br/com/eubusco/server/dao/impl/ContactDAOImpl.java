package br.com.eubusco.server.dao.impl;

import static br.com.eubusco.server.model.QContato.contato;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.eubusco.server.dao.ContactDAO;
import br.com.eubusco.server.model.Contato;

@Repository
public class ContactDAOImpl extends GenericDAOImpl<Contato> implements ContactDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@PostConstruct
	public void inicializar() {
		setEntityManager(entityManager);
	}

	@Override
	public List<Contato> getFromUser(Integer userCode) {
		return from().where(contato.codigoUsuario.eq(userCode)).list(contato);
	}

}
