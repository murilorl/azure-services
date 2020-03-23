package com.mrlima.app.functions.user;

import java.util.Properties;
import java.util.function.Function;

import com.mrlima.app.functions.KafkaService;
import com.mrlima.app.model.user.User;

/**
 * 
 * @author Murilo
 *
 */
public class UserPublish extends KafkaService<Properties, User> implements Function<User, User> {

	private static final String TOPIC = "users";

	@Override
	public User apply(User user) {

		Properties config = kafkaConfig.getConfig();
		String key = "key";

		publish(config, user, key);

		return new User(String.format("Published user record %s", user.getName()));
	}

	@Override
	protected void publish(Properties config, User model, String key) {
		executionContext.getLogger().info(String.format(
				"Publishing to topic %s, user record with key [%s] and value [%s]", TOPIC, key, model.toJsonString()));
	}
}
