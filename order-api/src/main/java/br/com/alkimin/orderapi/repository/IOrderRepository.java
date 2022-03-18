package br.com.alkimin.orderapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alkimin.orderapi.entity.Order;


/**
 * @author Vinicius Alkimin.
 */
@Repository
public interface IOrderRepository extends JpaRepository<Order, Long>{
	
	/**
	 * Find all orders.
	 * 
	 * @return Orders if found.
	 */	
	public Page<Order> findAll (Pageable pageable);
	
}
