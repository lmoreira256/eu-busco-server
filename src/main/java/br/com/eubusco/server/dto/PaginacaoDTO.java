package br.com.eubusco.server.dto;

import java.util.List;

public class PaginacaoDTO {

	private List<?> lista;
	private Long total;

	public PaginacaoDTO() {
	}

	public List<?> getLista() {
		return lista;
	}

	public void setLista(List<?> lista) {
		this.lista = lista;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
