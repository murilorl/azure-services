package com.mrlima.app.service;

import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

	// Kafka cluster
	private static final String BOOTSTRAP_SERVERS = "bootstrap.servers";
	private static final String SECURITY_PROTOCOL = "security.protocol";
	private static final String SASL_JAAS_CONFIG = "sasl.jaas.config";
	private static final String SSL_ENDPOINT_IDENTIFICATION_ALGORITHM = "ssl.endpoint.identification.algorithm";
	private static final String SASL_MECHANISM = "sasl.mechanism";

	// Confluent Cloud Schema Registry
	private static final String SCHEMA_REGISTRY_URL = "schema.registry.url";
	private static final String BASIC_AUTH_CREDENTIALS_SOURCE = "basic.auth.credentials.source";
	private static final String SCHEMA_REGISTRY_BASIC_AUTH_USER_INFO = "schema.registry.basic.auth.user.info";

	@Autowired
	private Environment env;

	public Properties loadConfig() {

		final Properties properties = new Properties();

		// Kafka cluster
		//
		properties.put(BOOTSTRAP_SERVERS, env.getProperty(BOOTSTRAP_SERVERS));
		properties.put(SECURITY_PROTOCOL, env.getProperty(SECURITY_PROTOCOL));

		properties.put(SASL_JAAS_CONFIG, env.getProperty(SASL_JAAS_CONFIG));

		properties.put(SSL_ENDPOINT_IDENTIFICATION_ALGORITHM, env.getProperty(SSL_ENDPOINT_IDENTIFICATION_ALGORITHM));
		properties.put(SASL_MECHANISM, env.getProperty(SASL_MECHANISM));

		// Confluent Cloud Schema Registry
		//
		properties.put(SCHEMA_REGISTRY_URL, env.getProperty(SCHEMA_REGISTRY_URL));
		properties.put(BASIC_AUTH_CREDENTIALS_SOURCE, env.getProperty(BASIC_AUTH_CREDENTIALS_SOURCE));
		properties.put(SCHEMA_REGISTRY_BASIC_AUTH_USER_INFO, env.getProperty(SCHEMA_REGISTRY_BASIC_AUTH_USER_INFO));

		// Additional properties
		properties.put(ProducerConfig.ACKS_CONFIG, "all");
		return properties;
	}
}
