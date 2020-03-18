package com.mrlima.app.functions;

import java.util.Optional;

import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.mrlima.app.model.BasicPublishResponse;
import com.mrlima.app.model.vendor.Vendor;

public class VendorHandler extends AzureSpringBootRequestHandler<Vendor, BasicPublishResponse> {

	@FunctionName("pubvendor")
	public BasicPublishResponse execute(@HttpTrigger(name = "request", methods = {
			HttpMethod.POST }, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<Vendor>> request,
			ExecutionContext context) {

		Vendor input = request.getBody().get();

		context.getLogger().info(String.format("Request for : [%s]", input.getClientId()));

		return handleRequest(request.getBody().get(), context);

	}

}
