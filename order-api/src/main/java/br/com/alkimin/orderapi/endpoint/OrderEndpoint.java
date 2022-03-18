package br.com.alkimin.orderapi.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alkimin.orderapi.entity.Order;
import br.com.alkimin.orderapi.published.APIGenericResponse;
import br.com.alkimin.orderapi.published.PagedObject;
import br.com.alkimin.orderapi.published.StatusResponse;
import br.com.alkimin.orderapi.service.OrderService;

/**
 * @author Vinicius Alkimin.
 */
@RestController
public class OrderEndpoint {

	/**
	 * Set the logger factory.
	 */
	static Logger logger = LoggerFactory.getLogger(OrderEndpoint.class);

	/**
	 * Set services.
	 */
	@Autowired
	private OrderService orderrService;

	/**
	 * End points definitions.
	 */
	
	@RequestMapping(method = RequestMethod.GET, value = "/private/list/order", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> list(@RequestParam(value = "page") Integer page) {

		PagedObject orders = null;

		try {

			orders = orderrService.list(page);

			if (orders.getTotalItens() <= 0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						new APIGenericResponse(orders, new StatusResponse("Order not found.", HttpStatus.NOT_FOUND)));
			}

			return ResponseEntity.ok(new APIGenericResponse(orders,
					new StatusResponse("Request API is successfully", HttpStatus.CREATED)));
		}

		catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIGenericResponse(false,
					new StatusResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)));
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/private/create/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create(@RequestBody Order order) {

		try {

			return ResponseEntity.ok(new APIGenericResponse(orderrService.create(order),
					new StatusResponse("Request API is successfully", HttpStatus.CREATED)));

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIGenericResponse(false,
					new StatusResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)));
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/private/update/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody Order order) {

		try {

			return ResponseEntity.ok(new APIGenericResponse(orderrService.update(order),
					new StatusResponse("Request API is successfully", HttpStatus.CREATED)));

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIGenericResponse(false,
					new StatusResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)));
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/private/delete/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@RequestBody Order order) {

		try {

			return ResponseEntity.ok(new APIGenericResponse(orderrService.delete(order),
					new StatusResponse("Request API is successfully", HttpStatus.CREATED)));

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIGenericResponse(false,
					new StatusResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)));
		}
	}
}
