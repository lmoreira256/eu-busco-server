package br.com.eubusco.server.dao;

import java.util.List;

import br.com.eubusco.server.model.Entrega;

public interface DeliveryDAO extends GenericDAO<Entrega> {

	public abstract List<Entrega> getUserDeliveries(Integer userCode);

}
