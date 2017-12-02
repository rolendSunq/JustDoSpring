package com.justdospring.myspring.chapter1.domain;

public class User {
	String id;
	String name;
	String password;
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
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("User [id=").append(id).append(", name=").append(name).append(", password=").append(password).append("]");
		return sb.toString();
	}

}
