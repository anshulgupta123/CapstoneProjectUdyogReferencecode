package com.stackroute.userservice.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Pageable;

public class PaginationResponseDto<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<T> paginatedData;
	private Pageable pageable;
	private Integer totalPages;
	private Long totalNoOfElements;

	public PaginationResponseDto() {
		super();
	}

	

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Long getTotalNoOfElements() {
		return totalNoOfElements;
	}

	public void setTotalNoOfElements(Long totalNoOfElements) {
		this.totalNoOfElements = totalNoOfElements;
	}

	public List<T> getPaginatedData() {
		return paginatedData;
	}

	public void setPaginatedData(List<T> paginatedData) {
		this.paginatedData = paginatedData;
	}

	public Pageable getPageable() {
		return pageable;
	}

	public void setPageable(Pageable pageable) {
		this.pageable = pageable;
	}



	@Override
	public String toString() {
		return "PaginationResponseDto [paginatedData=" + paginatedData + ", pageable=" + pageable + ", totalPages="
				+ totalPages + ", totalNoOfElements=" + totalNoOfElements + "]";
	}

	

}
