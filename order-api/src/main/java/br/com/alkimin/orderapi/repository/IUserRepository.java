package br.com.alkimin.orderapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alkimin.orderapi.entity.User;

/**
 * @author Vinicius Alkimin.
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
	
	/**
	 * Find all users activated.
	 * 
	 * @return User if found.
	 */	
	public Page<User> findAll (Specification<User> specification, Pageable pageable);

}
