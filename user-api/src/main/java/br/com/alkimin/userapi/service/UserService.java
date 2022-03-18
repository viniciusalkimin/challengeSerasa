package br.com.alkimin.userapi.service;

import br.com.alkimin.userapi.entity.User;
import br.com.alkimin.userapi.published.PagedObject;

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
	public PagedObject list (Integer page) throws Exception;
	
	/**
	 * Disable User.
	 * 
	 * @param User.
	 * @return True if disabled.
	 */	
	public boolean disable (User user) throws Exception;

}
