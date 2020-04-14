package br.com.eubusco.server.dao.impl;

import static br.com.eubusco.server.model.QEntrega.entrega;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.eubusco.server.dao.DeliveryDAO;
import br.com.eubusco.server.model.Entrega;

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
	public List<Entrega> fetchUserDeliveries(Integer userCode) {
		return sqlFrom().from(entrega).where(entrega.codigoCliente.eq(userCode)).list(entrega);
	}

}
