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
	public Long adquirirEntregasAbertasUsuario(Integer idUsuario) {
		return from().where(entrega.flagFinalizada.eq(Boolean.FALSE).and(entrega.codigoCliente.eq(idUsuario))
				.or(entrega.codigoEntregador.eq(idUsuario)).and(entrega.dataExclusao.isNull())
				.and(entrega.flagFinalizada.isFalse())).count();
	}

	@Override
	public Long adquirirTotalEntregasUsuario(Integer idUsuario) {
		return from().where(entrega.codigoCliente.eq(idUsuario).or(entrega.codigoEntregador.eq(idUsuario))).count();
	}

	@Override
	public List<Entrega> buscarAbertasCliente(Integer idUsuario) {
		return from().where(entrega.flagFinalizada.eq(Boolean.FALSE).and(entrega.codigoCliente.eq(idUsuario))
				.and(entrega.dataExclusao.isNull())).list(entrega);
	}

	@Override
	public List<Entrega> buscarAbertasEntregador(Integer idUsuario) {
		return from().where(entrega.flagFinalizada.eq(Boolean.FALSE).and(entrega.codigoEntregador.eq(idUsuario))
				.and(entrega.dataExclusao.isNull())).list(entrega);
	}

	@Override
	public List<Entrega> buscarDisponiveis() {
		return from().where(entrega.flagFinalizada.eq(Boolean.FALSE).and(entrega.codigoEntregador.isNull())
				.and(entrega.dataExclusao.isNull())).list(entrega);
	}

	@Override
	public List<Entrega> buscarTodasAbertas() {
		return from().where(entrega.flagFinalizada.eq(Boolean.FALSE).and(entrega.dataExclusao.isNull())).list(entrega);
	}

}
