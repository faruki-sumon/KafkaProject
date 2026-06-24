package com.KafkaUserEnd.KafkaEndUser.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConfig {

	private Logger logger= LoggerFactory.getLogger(KafkaConfig.class);
	
	@KafkaListener(topics = Constants.UPDATED_LOCATON_TOPIC,groupId = Constants.KAFKA_GROUP_ID)
	public void updatedLocation(String location) {
		System.out.println(location);
		this.logger.info("Location consumed====" + location);
		
	}
}
