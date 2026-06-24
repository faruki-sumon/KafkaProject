package com.KafkaDeliveryBoy.KafkaDelivery.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.KafkaDeliveryBoy.KafkaDelivery.constants.Constants;

@Configuration
public class KafkaConfig {

	@Bean
	  NewTopic topic() {
		return TopicBuilder
				.name(Constants.LOCATION_UPDATE_TOPIC)
//				.partitions(4)
//				.replicas(3)
				.build();
	}
}
