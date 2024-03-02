package com.epam.training.backend_services.avro_messaging;

import com.epam.training.backend_services.avro_messaging.service.MessageConsumer;
import com.epam.training.backend_services.avro_messaging.service.MessageProducer;
import com.epam.training.backend_services.avro_messaging.util.PropertyLoader;

import java.util.Properties;

public class Application {
    public static void main(String[] args) throws InterruptedException {
        PropertyLoader propertyLoader = new PropertyLoader();
        Properties properties = propertyLoader.loadProperties();

        MessageProducer producer = new MessageProducer(properties);
        MessageConsumer consumer = new MessageConsumer(properties);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
