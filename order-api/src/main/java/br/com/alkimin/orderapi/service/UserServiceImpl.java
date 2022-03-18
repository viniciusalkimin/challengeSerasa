package br.com.alkimin.orderapi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alkimin.orderapi.entity.User;
import br.com.alkimin.orderapi.published.PagedObject;
import br.com.alkimin.orderapi.repository.IUserRepository;
import br.com.alkimin.orderapi.security.EncoderService;

/**
 * @author Vinicius Alkimin.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserServiceImpl implements UserService {

	/**
	 * Set data repositories.
	 */
	@Autowired
	private IUserRepository iUserRepository;

	/**
	 * Set the logger factory.
	 */
	static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	/**
	 * Create new user.
	 * 
	 * @param User
	 * @return User if created.
	 */
	@CacheEvict(cacheNames = "Users", allEntries = true)
	@Override
	public User create(User user) throws Exception {

		logger.info("Creating new user");

		try {
			user.setCreated_at(new Date());
			user.setCpf(EncoderService.encrypt(user.getCpf()));
			user.setEmail(EncoderService.encrypt(user.getEmail()));
			user.setPhone_number(EncoderService.encrypt(user.getPhone_number()));
			return iUserRepository.save(user);

		} catch (Exception e) {
			logger.error("Error trying creat user.");
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * Update a user info.
	 * 
	 * @param User.
	 * @return User if updated.
	 */
	@CacheEvict(cacheNames = "Users", allEntries = true)
	@Override
	public User update(User user) throws Exception {

		logger.info("Updanting a user");

		try {

			User userUpdated = iUserRepository.findById(user.getId()).get();
			userUpdated.setUpdated_at(new Date());
			userUpdated.setName(user.getName());
			userUpdated.setCpf(EncoderService.encrypt(user.getCpf()));
			userUpdated.setEmail(EncoderService.encrypt(user.getEmail()));
			userUpdated.setPhone_number(EncoderService.encrypt(user.getPhone_number()));

			return iUserRepository.save(userUpdated);

		} catch (Exception e) {
			logger.error("Error trying update user.");
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * List paged of Users.
	 * 
	 * @param Page.
	 * @return List paged of users if found.
	 */
	@Cacheable(cacheNames = "Users")
	@Override
	public PagedObject list(Integer page) throws Exception {

		logger.info("Finding users.");

		try {

			PagedObject pagedObject = new PagedObject();

			Specification<User> specification = new Specification<User>() {

				private static final long serialVersionUID = -2664900775887691045L;

				@Override
				public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

					List<Predicate> predicates = new ArrayList<>();

					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("disabled"), false)));

					return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

				}

			};

			Page<User> pages = iUserRepository.findAll(specification,
					PageRequest.of(page, 3, Sort.by(Direction.ASC, "id")));

			List<User> users = new LinkedList<User>();

			for (User user : pages) {
				users.add(new User(user.getId(), user.getName(), EncoderService.decrypt(user.getCpf()),
						EncoderService.decrypt(user.getEmail()), EncoderService.decrypt(user.getPhone_number()),
						user.getCreated_at(), user.getUpdated_at(), user.isDisabled()));
			}

			pagedObject.setPage(page);
			pagedObject.setItens(users);
			pagedObject.setTotalItens(pages.getTotalElements());
			pagedObject.setTotalItensPage(pages.getNumberOfElements());
			pagedObject.setTotalPages(pages.getTotalPages());

			return pagedObject;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * Disable User.
	 * 
	 * @param User.
	 * @return True if disabled.
	 */
	@Override
	public boolean disable(User user) throws Exception {

		logger.info("Deleting a user");

		try {

			Optional<User> userDisabled = iUserRepository.findById(user.getId());

			if (userDisabled.isPresent()) {
				userDisabled.get().setDisabled(true);
				iUserRepository.save(userDisabled.get());
			}

			return true;

		} catch (Exception e) {
			logger.error("Error trying delete user.");
			e.printStackTrace();
		}
		return false;

	}

}
