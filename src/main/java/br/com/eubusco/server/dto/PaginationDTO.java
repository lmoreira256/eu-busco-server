package br.com.eubusco.server.dto;

import java.util.List;

public class PaginationDTO {

	private List<?> list;
	private Long count;

	public PaginationDTO() {
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}
