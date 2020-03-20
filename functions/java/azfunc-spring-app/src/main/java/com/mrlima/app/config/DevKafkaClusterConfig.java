package com.mrlima.app.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

@Configuration
@Profile("dev")
public class DevKafkaClusterConfig implements KafkaClusterConfig {
	
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
	
//	@Value("${sasl.jaas.config}")
//	private String saslJaasConfig;
//	
//	@Value("${schema.registry.basic.auth.user.info}")
//	private String schemaRegistryBasicAuthUserInfo;
	
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
