package com.mrlima.app.handler.azure;

import java.util.Optional;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.mrlima.app.model.user.User;

public class UserHandler extends AzureSpringBootRequestHandler<User, User> {

	@FunctionName("userGet")
	public User get(@HttpTrigger(name = "request", methods = { HttpMethod.GET,
			HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS, route = "users") HttpRequestMessage<Optional<User>> request,
			ExecutionContext context) {

		User user = null;

		if (request.getQueryParameters().get("name") != null)
			user = new User(request.getQueryParameters().get("name"));

		if (user == null)
			user = request.getBody().get();

		context.getLogger().info("Request to get user with name: " + user.getName());

		return handleRequest(user, context);
	}

	@FunctionName("userPublish")
	public User publish(@HttpTrigger(name = "request", methods = {
			HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS, route = "users/pub") HttpRequestMessage<User> request,
			ExecutionContext context) {

		context.getLogger().info(String.format("Request to publish user with data %s", request.getBody()));

		return handleRequest(request.getBody(), context);
	}

}
