package com.practice.gtm.spring_boot_kafka_producer_demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaConfig {

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(AppConfig.LOCATION_EVENT)
                .build();
    }
}
