package br.com.eubusco.server.dao.impl;

import static br.com.eubusco.server.model.QEntrega.entrega;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.eubusco.server.dao.EntregaDAO;
import br.com.eubusco.server.model.Entrega;

@Repository
public class EntregaDAOImpl extends GenericDAOImpl<Entrega> implements EntregaDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@PostConstruct
	public void inicializar() {
		setEntityManager(entityManager);
	}

	@Override
	public Long adquirirEntragasAbertasUsuario(Integer codigoUsuario) {
		return from().where(entrega.flagFinalizada.eq(Boolean.FALSE).and(entrega.codigoCliente.eq(codigoUsuario))
				.or(entrega.codigoEntregador.eq(codigoUsuario))).count();
	}

	@Override
	public Long adquirirTotalEntragasUsuario(Integer codigoUsuario) {
		return from().where(entrega.codigoCliente.eq(codigoUsuario).or(entrega.codigoEntregador.eq(codigoUsuario)))
				.count();
	}

}
