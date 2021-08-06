package com.vinodh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="auditLog")
public class AuditLog {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_user")
	@SequenceGenerator(name = "seq_gen_user", sequenceName = "SEQ_GEN_ADUDITLOG",allocationSize = 1, initialValue = 1)
	private Integer user_id;
	private String username;
	private String password;
	private String email;
	
	public AuditLog(Integer user_id, String username, String password, String email) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	public AuditLog() {
	
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "AuditLog [user_id=" + user_id + ", username=" + username + ", password=" + password + ", email=" + email
				+ "]";
	}
	
}
