package com.mrlima.app.functions;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class KafkaConfig {
	
	@Autowired
	protected Environment env;

	static final String BOOTSTRAP_SERVERS = "bootstrap.servers";
	static final String SECURITY_PROTOCOL = "security.protocol";
	static final String SASL_JAAS_CONFIG = "sasl.jaas.config";
	static final String SSL_ENDPOINT_IDENTIFICATION_ALGORITHM = "ssl.endpoint.identification.algorithm";
	static final String SASL_MECHANISM = "sasl.mechanism";

	// Confluent Cloud Schema Registry
	static final String SCHEMA_REGISTRY_URL = "schema.registry.url";
	static final String BASIC_AUTH_CREDENTIALS_SOURCE = "basic.auth.credentials.source";
	static final String SCHEMA_REGISTRY_BASIC_AUTH_USER_INFO = "schema.registry.basic.auth.user.info";

	@Bean
	public Properties getConfig() {
		
		Properties props = new Properties();
		props.put(BOOTSTRAP_SERVERS, env.getProperty(BOOTSTRAP_SERVERS));
		props.put(SECURITY_PROTOCOL, env.getProperty(SECURITY_PROTOCOL));
		props.put(SASL_JAAS_CONFIG, env.getProperty(SASL_JAAS_CONFIG));
		props.put(SASL_MECHANISM, env.getProperty(SASL_MECHANISM));
		props.put(SSL_ENDPOINT_IDENTIFICATION_ALGORITHM, env.getProperty(SSL_ENDPOINT_IDENTIFICATION_ALGORITHM));

		// Confluent Cloud Schema Registry
		props.put(SCHEMA_REGISTRY_URL, env.getProperty(SCHEMA_REGISTRY_URL));
		props.put(BASIC_AUTH_CREDENTIALS_SOURCE, env.getProperty(BASIC_AUTH_CREDENTIALS_SOURCE));
		props.put(SCHEMA_REGISTRY_BASIC_AUTH_USER_INFO, env.getProperty(SCHEMA_REGISTRY_BASIC_AUTH_USER_INFO));

		return props;
	}

}
