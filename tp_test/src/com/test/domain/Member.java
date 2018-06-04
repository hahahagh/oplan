package com.test.domain;

import java.sql.Timestamp;

public class Member {
	
	public static final int ID_OK_PASSWD_OK = 1;
	public static final int ID_OK_PASSWD_FAIL = 0;
	public static final int ID_FAIL_PASSWD_FAIL = -1;
	
	private String id;
	private String passwd;
	private String name;
	private Timestamp reg_date;
	private Integer age;
	private String gender;
	private String email;
	
	public Member() {
	}
	
	public Member(String id, String passwd, String name, Timestamp reg_date, Integer age, String gender, String email) {
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.reg_date = reg_date;
		this.age = age;
		this.gender = gender;
		this.email = email;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Member [id=");
		builder.append(id);
		builder.append(", passwd=");
		builder.append(passwd);
		builder.append(", name=");
		builder.append(name);
		builder.append(", reg_date=");
		builder.append(reg_date);
		builder.append(", age=");
		builder.append(age);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}
}
