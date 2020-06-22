package br.com.eubusco.server.dao.impl;

import static br.com.eubusco.server.model.QAvaliacao.avaliacao;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.path.NumberPath;

import br.com.eubusco.server.dao.AvaliacaoDAO;
import br.com.eubusco.server.model.Avaliacao;
import br.com.eubusco.server.util.BigDecimalUtil;

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
	public BigDecimal buscarNotaUsuario(Integer codigoUsuario, Integer tipoUsuario) {
		NumberPath<BigDecimal> notaUsuario = new NumberPath<>(BigDecimal.class, "notaUsuario");

		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if (tipoUsuario == 2) {
			booleanBuilder.and(avaliacao.codigoCliente.eq(codigoUsuario));
			booleanBuilder.and(avaliacao.codigoTipoAvaliacao.in(2, 3));
		} else if (tipoUsuario == 3) {
			booleanBuilder.and(avaliacao.codigoEntregador.eq(codigoUsuario));
			booleanBuilder.and(avaliacao.codigoTipoAvaliacao.in(1, 3));
		}

		long avaliacoes = from().where(booleanBuilder).count();

		if (avaliacoes > 0) {
			return from().where(booleanBuilder).singleResult(avaliacao.nota.sum().coalesce(5L).asNumber()
					.divide(avaliacoes).castToNum(BigDecimal.class).coalesce(BigDecimalUtil.CINCO).as(notaUsuario));
		} else {
			return BigDecimalUtil.CINCO;
		}
	}

}
