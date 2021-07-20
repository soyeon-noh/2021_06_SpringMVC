package com.callor.ajax.model;

public class UserDTO {
	
	private String user_id;
	private String password;
	private String tel;
	private int age;
	
	
	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	@Override
	public String toString() {
		return "UserDTO [user_id=" + user_id + ", password=" + password + ", tel=" + tel + ", age=" + age + "]";
	}
	
	
	
}
