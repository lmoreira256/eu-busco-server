package br.com.eubusco.server.bo;

import java.math.BigDecimal;

import br.com.eubusco.server.model.Avaliacao;

public interface AvaliacaoBO {

	public abstract Boolean salvar(Avaliacao avaliacao);

	public abstract BigDecimal buscarNotaUsuario(Integer codigoUsuario, Integer tipoUsuario);

}
