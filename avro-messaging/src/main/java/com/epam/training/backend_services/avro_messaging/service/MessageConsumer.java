package com.epam.training.backend_services.avro_messaging.service;

import com.epam.training.backend_services.avro_messaging.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Slf4j
@RequiredArgsConstructor
public class MessageConsumer extends Thread {

    private final Properties properties;

    public void run() {

        log.info("Starting producer thread");
        try (KafkaConsumer<String, Message> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(Arrays.asList("avro-topic"));
            log.info("Subscribed to topic {}", properties.getProperty("kafka.topic"));

            Map<String, List<PartitionInfo>> listTopics = consumer.listTopics();
            log.info("list of topic size: {}", listTopics.size());

            for(String topic : listTopics.keySet()){
                log.info("topic name: {}", topic);
            }

            while (true) {
                ConsumerRecords<String, Message> records = consumer.poll(Duration.ofMillis(100L));
                for (ConsumerRecord<String, Message> record : records) {
                    log.info("Consumed message partition = {}, offset = {}, key = {}, payload = {}",
                            record.partition(), record.offset(), record.key(), record.value().getPayload());
                }
            }
        } catch (Exception e) {
            log.error("Caught error", e);
        }
    }
}
