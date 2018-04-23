package com.justdospring.myspring.ch02.part8.junit_fixture;

public class User {
	String id;
	String name;
	String password;
	String email;
	
	public User() {
		// empty
	}
	
	public User(String id, String name, String password, String email) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		StringBuffer sb = new StringBuffer();
		sb.append("User [id=").append(id).append(", name=").append(name).append(", password=").append(password).append(", email=").append(email).append("]");
		return sb.toString();
	}

}
