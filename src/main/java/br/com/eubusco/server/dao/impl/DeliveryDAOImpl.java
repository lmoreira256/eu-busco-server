package br.com.eubusco.server.dao.impl;

import static br.com.eubusco.server.model.QEntrega.entrega;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.eubusco.server.dao.DeliveryDAO;
import br.com.eubusco.server.model.Entrega;
import br.com.eubusco.server.util.LongUtil;

@Repository
public class DeliveryDAOImpl extends GenericDAOImpl<Entrega> implements DeliveryDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@PostConstruct
	public void inicializar() {
		setEntityManager(entityManager);
	}

	@Override
	public List<Entrega> getUserDeliveries(Integer userCode, Integer page) {
		return from().where(entrega.codigoCliente.eq(userCode).and(entrega.flagFinalizada.eq(Boolean.FALSE)))
				.limit(LongUtil.QUATRO).offset((page - 1) * 4).list(entrega);
	}

	@Override
	public Long getCountUserDeliveries(Integer userCode) {
		return from().where(entrega.codigoCliente.eq(userCode).and(entrega.flagFinalizada.eq(Boolean.FALSE))).count();
	}

}
