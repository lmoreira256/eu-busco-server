package br.com.eubusco.server.bo;

import java.util.List;

import br.com.eubusco.server.model.Entrega;

public interface EntregaBO {

	public abstract Boolean salvar(Entrega entrega);

	public abstract List<Entrega> buscarAbertasCliente(Integer idUsuario);

}
