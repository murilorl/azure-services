package com.mrlima.app.functions.user;

import java.util.function.Function;

import com.mrlima.app.model.user.User;

public class UserGet implements Function<User, User> {

	@Override
	public User apply(User user) {
		return new User(String.format("Hello %s", user.getName()));
	}
}
