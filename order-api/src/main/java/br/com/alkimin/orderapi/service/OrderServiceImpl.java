package br.com.alkimin.orderapi.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.alkimin.orderapi.entity.Order;
import br.com.alkimin.orderapi.published.PagedObject;
import br.com.alkimin.orderapi.repository.IOrderRepository;

/**
 * @author Vinicius Alkimin.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrderServiceImpl implements OrderService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Set data repositories.
	 */
	@Autowired
	private IOrderRepository iOrderRepository;

	/**
	 * Set the logger factory.
	 */
	static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	/**
	 * Create new Order.
	 * 
	 * @param Order
	 * @return Order if created.
	 */
	@CacheEvict(cacheNames = "Orders", allEntries = true)
	@Override
	public Order create(Order order) throws Exception {

		logger.info("Creating new order");

		try {

			order.setCreated_at(new Date());
			order.setTotal_value(calcTotalValue(order));
			return iOrderRepository.save(order);

		} catch (Exception e) {
			logger.error("Error trying creat user.");
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * Update a order info.
	 * 
	 * @param Order.
	 * @return Order if updated.
	 */
	@CacheEvict(cacheNames = "Orders", allEntries = true)
	@Override
	public Order update(Order order) throws Exception {

		logger.info("Updanting a order");

		try {

			Order orderUpdated = iOrderRepository.findById(order.getId()).get();
			orderUpdated.setUpdated_at(new Date());
			orderUpdated.setItem_description(order.getItem_description());
			orderUpdated.setItem_quantity(order.getItem_quantity());
			orderUpdated.setItem_price(order.getItem_price());
			orderUpdated.setTotal_value(calcTotalValue(orderUpdated));

			return iOrderRepository.save(orderUpdated);

		} catch (Exception e) {
			logger.error("Error trying update order.");
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * List paged of Orders.
	 * 
	 * @param Page and keyword filter.
	 * @return List paged of orders if found.
	 */
	@Cacheable(cacheNames = "Orders", key = "#root.method.name")
	@Override
	public PagedObject list(Integer page) throws Exception {

		logger.info("Finding orders.");

		try {

			PagedObject pagedObject = new PagedObject();

			Page<Order> pages = iOrderRepository.findAll(PageRequest.of(page, 3, Sort.by(Direction.ASC, "id")));


			List<Order> orders = new LinkedList<Order>();

			for (Order order : pages) {
				orders.add(new Order(order.getId(), order.getId_user(), order.getItem_description(), order.getItem_quantity(), order.getItem_price(),
						order.getTotal_value(), order.getCreated_at(), order.getUpdated_at()));
			}

			pagedObject.setPage(page);
			pagedObject.setItens(orders);
			pagedObject.setTotalItens(pages.getTotalElements());
			pagedObject.setTotalItensPage(pages.getNumberOfElements());
			pagedObject.setTotalPages(pages.getTotalPages());

			return pagedObject;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	/**
	 * Delete Order.
	 * 
	 * @param Order.
	 * @return True if deleted.
	 */
	@Override
	public boolean delete(Order order) throws Exception {

		logger.info("Deleting a order");

		try {

			Optional<Order> orderDeleted = iOrderRepository.findById(order.getId());

			if (orderDeleted.isPresent()) {
				iOrderRepository.delete(orderDeleted.get());
			}

			return true;

		} catch (Exception e) {
			logger.error("Error trying delete order.");
			e.printStackTrace();
		}

		return false;

	}

	/**
	 * Calculate a total value of an order.
	 * 
	 * @param Order.
	 * @return BigDecimal with a result .
	 */
	@Override
	public BigDecimal calcTotalValue(Order order) {
		return new BigDecimal(order.getItem_quantity()).multiply(order.getItem_price());
	}

}
