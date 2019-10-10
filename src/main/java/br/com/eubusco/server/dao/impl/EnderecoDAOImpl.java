package br.com.eubusco.server.dao.impl;

import static br.com.eubusco.server.model.QEndereco.endereco;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.eubusco.server.dao.EnderecoDAO;
import br.com.eubusco.server.model.Endereco;

@Repository
public class EnderecoDAOImpl extends GenericDAOImpl<Endereco> implements EnderecoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@PostConstruct
	public void inicializar() {
		setEntityManager(entityManager);
	}

	@Override
	public List<Endereco> adquirirTodosUsuario(Integer idUsuario) {
		return from().where(endereco.codigoUsuario.eq(idUsuario)).list(endereco);
	}

}
