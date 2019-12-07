package br.com.eubusco.server.dao.impl;

import static br.com.eubusco.server.model.QAvaliacao.avaliacao;
import static br.com.eubusco.server.model.QEntrega.entrega;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mysema.query.sql.SQLSubQuery;

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

	@Override
	public List<Entrega> buscarEntregasAvaliacao(Integer codigoUsuario, Integer codigoTipoUsuario) {
		SQLSubQuery subAvaliacao = sqlSubQuery().from(avaliacao)
				.where(avaliacao.codigoTipoAvaliacao.eq(codigoTipoUsuario == 2 ? 1 : 2));

		return from().where(entrega.codigoCliente.eq(codigoUsuario).or(entrega.codigoEntregador.eq(codigoUsuario))
				.and(entrega.id.notIn(subAvaliacao.list(avaliacao.codigoEntrega)))
				.and(entrega.flagFinalizada.eq(Boolean.TRUE))).list(entrega);
	}

	@Override
	public Integer buscarCodigoCliente(Integer codigoEntrega) {
		return from().where(entrega.id.eq(codigoEntrega)).uniqueResult(entrega.codigoCliente);
	}

	@Override
	public Integer buscarCodigoEntregador(Integer codigoEntrega) {
		return from().where(entrega.id.eq(codigoEntrega)).uniqueResult(entrega.codigoEntregador);
	}

}
