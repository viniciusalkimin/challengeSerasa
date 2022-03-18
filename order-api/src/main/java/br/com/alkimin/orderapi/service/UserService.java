package br.com.alkimin.orderapi.service;

import br.com.alkimin.orderapi.entity.User;
import br.com.alkimin.orderapi.published.PagedObject;

/**
 * @author Vinicius Alkimin.
 */
public interface UserService {
	
	/**
	 * Create new user.
	 * 
	 * @param User
	 * @return User if created.
	 */
	public User create (User user) throws Exception;
	
	/**
	 * Update a user info.
	 * 
	 * @param User.
	 * @return User if updated.
	 */
	public User update (User user) throws Exception;
	
	/**
	 * List paged of Users.
	 * 
	 * @param Page and keyword filter.
	 * @return List paged of users if found.
	 */
	PagedObject list(Integer page) throws Exception;
	
	/**
	 * Disable User.
	 * 
	 * @param User.
	 * @return True if disabled.
	 */	
	public boolean disable (User user) throws Exception;

	/**
	 * List paged of Users.
	 * 
	 * @param Page.
	 * @return List paged of users if found.
	 */
	

}
