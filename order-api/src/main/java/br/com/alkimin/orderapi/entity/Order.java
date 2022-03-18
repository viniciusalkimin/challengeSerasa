package br.com.alkimin.orderapi.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Vinicius Alkimin.
 */
@Entity
@Table(name = "Orders")
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order", nullable = false, unique = true)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user")
	private User id_user;

	@Column(name = "item_description", nullable = false, unique = false)
	private String item_description;

	@Column(name = "item_quantity", nullable = false, unique = false)
	private Long item_quantity;

	@Column(name = "item_price", nullable = false, unique = false)
	private BigDecimal item_price;

	@Column(name = "total_value", nullable = false, unique = false)
	private BigDecimal total_value;

	@Column(name = "created_at", nullable = false, unique = false)
	private Date created_at;

	@Column(name = "updated_at", nullable = true, unique = false)
	private Date updated_at;

	public Order() {
	}

	public Order(Long id, User id_user, String item_description, Long item_quantity, BigDecimal item_price,
			BigDecimal total_value, Date created_at, Date updated_at) {
		this.id = id;
		this.id_user = id_user;
		this.item_description = item_description;
		this.item_quantity = item_quantity;
		this.item_price = item_price;
		this.total_value = total_value;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getId_user() {
		return id_user;
	}

	public void setId_user(User id_user) {
		this.id_user = id_user;
	}

	public String getItem_description() {
		return item_description;
	}

	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}

	public Long getItem_quantity() {
		return item_quantity;
	}

	public void setItem_quantity(Long item_quantity) {
		this.item_quantity = item_quantity;
	}

	public BigDecimal getItem_price() {
		return item_price;
	}

	public void setItem_price(BigDecimal item_price) {
		this.item_price = item_price;
	}

	public BigDecimal getTotal_value() {
		return total_value;
	}

	public void setTotal_value(BigDecimal total_value) {
		this.total_value = total_value;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
