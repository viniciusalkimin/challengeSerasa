package br.com.alkimin.userapi.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alkimin.userapi.entity.User;
import br.com.alkimin.userapi.published.APIGenericResponse;
import br.com.alkimin.userapi.published.PagedObject;
import br.com.alkimin.userapi.published.StatusResponse;
import br.com.alkimin.userapi.service.UserService;

/**
 * @author Vinicius Alkimin.
 */
@RestController
public class UserEndpoint {
	
	/**
	 * Set the logger factory.
	 */
	static Logger logger = LoggerFactory.getLogger(UserEndpoint.class);

	/**
	 * Set services.
	 */
	@Autowired
	private UserService userService;

	/**
	 * End points definitions.
	 */
	
	@RequestMapping(method = RequestMethod.GET, value = "/private/list/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> list (@RequestParam(value="page") Integer page) {
		
		PagedObject users = null;
		
		try {
			
			users = userService.list(page);
			
			if (users.getTotalItens() <= 0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIGenericResponse(users, new StatusResponse("User not found.", HttpStatus.NOT_FOUND)));
			}
			
			return ResponseEntity.ok(new APIGenericResponse(users,	new StatusResponse("Request API is successfully", HttpStatus.CREATED)));
		}

		catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIGenericResponse(false,
					new StatusResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)));
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/private/create/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> create (@RequestBody User user) {

		try {
			
			return ResponseEntity.ok(new APIGenericResponse(userService.create(user),
					new StatusResponse("Request API is successfully", HttpStatus.CREATED)));
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIGenericResponse(false,
					new StatusResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)));
		}
	}
	
	@CacheEvict(cacheNames = "Users", allEntries = true)
	@RequestMapping(method = RequestMethod.PUT, value = "/private/update/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update (@RequestBody User user) {

		try {
			
			return ResponseEntity.ok(new APIGenericResponse(userService.update(user),
					new StatusResponse("Request API is successfully", HttpStatus.CREATED)));
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIGenericResponse(false,
					new StatusResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)));
		}
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/private/disable/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete (@RequestBody User user) {

		try {
			
			return ResponseEntity.ok(new APIGenericResponse(userService.disable(user),
					new StatusResponse("Request API is successfully", HttpStatus.CREATED)));
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIGenericResponse(false,
					new StatusResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)));
		}
	}
}
