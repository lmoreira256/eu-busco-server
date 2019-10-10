package br.com.eubusco.server.dao.impl;

import static br.com.eubusco.server.model.QAvaliacao.avaliacao;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.path.NumberPath;

import br.com.eubusco.server.dao.AvaliacaoDAO;
import br.com.eubusco.server.model.Avaliacao;

@Repository
public class AvaliacaoDAOImpl extends GenericDAOImpl<Avaliacao> implements AvaliacaoDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@PostConstruct
	public void inicializar() {
		setEntityManager(entityManager);
	}

	@Override
	public Long adiquirirNotaUsuario(Integer codigoUsuario) {
		NumberPath<Long> notaUsuario = new NumberPath<>(Long.class, "notaUsuario");

		BooleanBuilder booleanBuilder = new BooleanBuilder();
		booleanBuilder.or(avaliacao.codigoCliente.eq(codigoUsuario));
		booleanBuilder.or(avaliacao.codigoEntregador.eq(codigoUsuario));

		return from().where(booleanBuilder).singleResult(avaliacao.nota.sum().coalesce(5L).as(notaUsuario));
	}

}
