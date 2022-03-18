package br.com.alkimin.userapi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Vinicius Alkimin.
 */
@Entity
@Table(name = "Users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user", nullable = false, unique = true)
	private Long id;

	@Column(name = "name", nullable = false, unique = false)
	private String name;

	@Column(name = "cpf", nullable = false, unique = true)
	private String cpf;

	@Column(name = "email", nullable = false, unique = false)
	private String email;

	@Column(name = "phone_number", nullable = false, unique = false)
	private String phone_number;

	@Column(name = "created_at", nullable = false, unique = false)
	private Date created_at;

	@Column(name = "updated_at", nullable = true, unique = false)
	private Date updated_at;

	@Column(name = "disabled", nullable = false, unique = false)
	private boolean disabled;

	public User() {
	}

	public User(Long id, String name, String cpf, String email, String phone_number, Date created_at,
			Date updated_at, boolean disabled) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.phone_number = phone_number;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.disabled = disabled;
	}

	public Long getId_user() {
		return id;
	}

	public void setId_user(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
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

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
