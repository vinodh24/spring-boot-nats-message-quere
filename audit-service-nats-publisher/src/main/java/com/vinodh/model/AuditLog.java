package com.vinodh.model;

import java.io.Serializable;

public class AuditLog implements Serializable{

	private static final long serialVersionUID = 436067781555681479L;
	
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
