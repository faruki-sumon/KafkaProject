package com.KafkaDeliveryBoy.KafkaDelivery.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.KafkaDeliveryBoy.KafkaDelivery.constants.Constants;

@Service
public class KafkaService {
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate ; 
	private Logger logger = LoggerFactory.getLogger(KafkaService.class);
 

	public boolean updateLocation(String location) {

			this.kafkaTemplate.send(Constants.LOCATION_UPDATE_TOPIC, location);
			this.logger.info("Location produced ::: "+location );
			System.out.println("---------------------------");
		
		return true;
	}

}
