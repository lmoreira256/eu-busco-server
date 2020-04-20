package br.com.eubusco.server.dao.impl;

import static br.com.eubusco.server.model.QUsuario.usuario;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.eubusco.server.dao.UserDAO;
import br.com.eubusco.server.model.Usuario;

@Repository
public class UserDAOImpl extends GenericDAOImpl<Usuario> implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@PostConstruct
	public void inicializar() {
		setEntityManager(entityManager);
	}

	@Override
	public Usuario buscarPorLogin(String login) {
		return from().where(usuario.login.eq(login)).uniqueResult(usuario);
	}

	@Override
	public String getNameFromUser(Integer userCode) {
		return from().where(usuario.id.eq(userCode)).uniqueResult(usuario.nome);
	}

}
