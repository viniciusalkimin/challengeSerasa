package br.com.alkimin.orderapi.published;

import java.io.Serializable;

/**
 * @author Vinicius Alkimin.
 */
public class PagedObject implements Serializable{

	/**
	 * 
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 296879070807415843L;

	
	private Object itens;
	private Integer page;
	private Long totalItens;
	private Integer totalItensPage;
	private Integer totalPages;

	public PagedObject() {
	}

	public Object getItens() {
		return itens;
	}

	public void setItens(Object itens) {
		this.itens = itens;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Long getTotalItens() {
		return totalItens;
	}

	public void setTotalItens(Long totalItens) {
		this.totalItens = totalItens;
	}

	public Integer getTotalItensPage() {
		return totalItensPage;
	}

	public void setTotalItensPage(Integer totalItensPage) {
		this.totalItensPage = totalItensPage;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

}
