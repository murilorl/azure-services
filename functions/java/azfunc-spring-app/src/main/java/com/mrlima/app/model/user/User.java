package com.mrlima.app.model.user;

import com.mrlima.app.model.BaseModel;

public class User extends BaseModel {

	private String name;

	public User(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toJsonString() {
		return super.toJsonString();
	}

}
