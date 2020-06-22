package br.com.eubusco.server.bo;

import br.com.eubusco.server.model.Endereco;

public interface AddressBO {

	public abstract Endereco getAddressFromCode(Integer addressCode);

}
