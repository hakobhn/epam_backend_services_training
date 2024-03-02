package com.epam.training.backend_services.avro_messaging.service;

import com.epam.training.backend_services.avro_messaging.model.Message;
import com.epam.training.backend_services.avro_messaging.util.RandomUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.sql.Timestamp;
import java.util.Properties;

import static com.epam.training.backend_services.avro_messaging.util.RandomUtils.getRandomNumber;

@Slf4j
@RequiredArgsConstructor
public class MessageProducer extends Thread {

    private final Properties properties;

    public void run() {
        log.info("Starting producer thread");
        try (KafkaProducer<String, Message> producer = new KafkaProducer<>(properties)) {
            while (true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int id = getRandomNumber(0, 100);
                log.info("Produced new message with id {}", id);
                producer.send(new ProducerRecord<>(properties.getProperty("kafka.topic"), String.valueOf(id), getMessage(id)));
            }
        } catch (Exception e) {
            log.error("Caught error", e);
        }
    }

    public static Message getMessage(int id) {
        return Message.newBuilder()
                .setId(id)
                .setTimestamp(new Timestamp(System.currentTimeMillis()).toString())
                .setPayload(RandomUtils.getRandomSentence())
                .build();
    }

}
