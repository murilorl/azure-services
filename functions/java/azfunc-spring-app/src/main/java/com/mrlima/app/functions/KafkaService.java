package com.mrlima.app.functions;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;

import com.microsoft.azure.functions.ExecutionContext;
import com.mrlima.app.model.BaseModel;

/**
 * 
 * @author Murilo
 *
 * @param <P>
 * @param <M>
 */
public abstract class KafkaService<P extends Properties, M extends BaseModel> {

	@Autowired
	protected KafkaConfig kafkaConfig;

	@Autowired
	protected ExecutionContext executionContext;

	protected abstract void publish(P config, M model, String key);

}
