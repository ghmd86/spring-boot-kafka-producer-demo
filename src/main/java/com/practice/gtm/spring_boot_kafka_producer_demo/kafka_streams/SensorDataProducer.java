package com.practice.gtm.spring_boot_kafka_producer_demo.kafka_streams;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class SensorDataProducer {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "172.31.238.74:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for(int i=0; i<100; i++) {
            String sensor = "Sensor-" + i;
            String value = "Temperature: " + (20 + Math.random() * 30);
            producer.send(new ProducerRecord<>("sensor-data", sensor, value));
        }

        producer.close();
    }
}
