package com.practice.gtm.spring_boot_kafka_producer_demo.service;

import com.practice.gtm.spring_boot_kafka_producer_demo.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    public void updateLocation(String location) throws InterruptedException {
        Thread.sleep(10);
        kafkaTemplate.send(AppConfig.LOCATION_EVENT, location);
    }
}
