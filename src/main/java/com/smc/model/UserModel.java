package com.smc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "user_table")
public class UserModel {
	@Id
	@GeneratedValue
	@Column(name = "id")
	@JsonProperty("userId")
	int userId;
	@Column(name = "name")
	@JsonProperty("userName")
	String userName;
	@Column(name = "message")
	@JsonProperty("userMessage")
	String userMessage;

	public UserModel() {

	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

}