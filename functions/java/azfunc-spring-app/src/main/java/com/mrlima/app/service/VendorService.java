package com.mrlima.app.service;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrlima.app.config.KafkaClusterConfig;
import com.mrlima.app.model.BasicPublishResponse;
import com.mrlima.app.model.vendor.Vendor;

import io.confluent.kafka.serializers.KafkaJsonSerializer;

@Service
public class VendorService {

	@Autowired
	private KafkaClusterConfig kafkaClusterConfig;

	Logger logger = LoggerFactory.getLogger(VendorService.class);

	private final String TOPIC = "vendor";

	public BasicPublishResponse publishVendorRecord(Vendor vendor) {

		Properties config = kafkaClusterConfig.getConfig();

		// Additional properties
		config.put(ProducerConfig.ACKS_CONFIG, "all");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaJsonSerializer.class.getName());

		// Instantiate the producer
		Producer<String, Vendor> producer = new KafkaProducer<String, Vendor>(config);

		// TODO: define record key, if any
		String key = null;

		producer.send(new ProducerRecord<String, Vendor>(TOPIC, key, vendor), new Callback() {
			@Override
			public void onCompletion(RecordMetadata m, Exception e) {
				if (e != null) {
					logger.warn(
							String.format("Failed to produce record to topic %s. Error message: %s", e.getMessage()));
//					targetContext.getLogger().warning(
//							String.format("Failed to produce record to topic %s. Error message: %s%n", e.getMessage()));
				} else {
					logger.info(String.format("Produced record to topic %s partition [%d] @ offset %d%n", m.topic(),
							m.partition(), m.offset()));
//					targetContext.getLogger()
//							.info(String.format("Produced record to topic %s partition [%d] @ offset %d%n", m.topic(),
//									m.partition(), m.offset()));
				}
			}
		});

		producer.flush();
		producer.close();

		return new BasicPublishResponse("Response provided by the service " + vendor.getAccountNumber());

	}
}
