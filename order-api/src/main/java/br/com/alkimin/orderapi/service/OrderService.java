package br.com.alkimin.orderapi.service;

import java.math.BigDecimal;

import br.com.alkimin.orderapi.entity.Order;
import br.com.alkimin.orderapi.published.PagedObject;

/**
 * @author Vinicius Alkimin.
 */
public interface OrderService {
	
	/**
	 * Create new order.
	 * 
	 * @param Order
	 * @return Order if created.
	 */
	public Order create (Order order) throws Exception;
	
	/**
	 * Update a order info.
	 * 
	 * @param Order.
	 * @return Order if updated.
	 */
	public Order update (Order order) throws Exception;
	
	/**
	 * List paged of Orders.
	 * 
	 * @param Page and keyword filter.
	 * @return List paged of orders if found.
	 */
	public PagedObject list (Integer page) throws Exception;
	
	/**
	 * Delete Order.
	 * 
	 * @param Order.
	 * @return True if deleted.
	 */	
	public boolean delete (Order order) throws Exception;
	
	public BigDecimal calcTotalValue(Order order);

}
