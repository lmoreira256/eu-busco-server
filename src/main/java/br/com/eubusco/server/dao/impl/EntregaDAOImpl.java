package br.com.eubusco.server.dao.impl;

import static br.com.eubusco.server.model.QEntrega.entrega;

import java.util.List;

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
	public Long adquirirEntragasAbertasUsuario(Integer idUsuario) {
		return from().where(entrega.flagFinalizada.eq(Boolean.FALSE).and(entrega.codigoCliente.eq(idUsuario))
				.or(entrega.codigoEntregador.eq(idUsuario))).count();
	}

	@Override
	public Long adquirirTotalEntragasUsuario(Integer idUsuario) {
		return from().where(entrega.codigoCliente.eq(idUsuario).or(entrega.codigoEntregador.eq(idUsuario))).count();
	}

	@Override
	public List<Entrega> buscarAbertasCliente(Integer idUsuario) {
		return from().where(entrega.flagFinalizada.eq(Boolean.FALSE).and(entrega.codigoCliente.eq(idUsuario)))
				.list(entrega);
	}

}
