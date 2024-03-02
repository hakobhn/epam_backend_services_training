package com.epam.training.backend_services.avro_messaging.avro.serialization;

import com.epam.training.backend_services.avro_messaging.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class MessageDeserializer implements Deserializer<Message> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Message deserialize(String topic, byte[] bytes) {
        DatumReader<Message> reader = new SpecificDatumReader<>(Message.class);
        Decoder decoder;
        try {
            decoder = DecoderFactory.get()
                    .jsonDecoder(Message.getClassSchema(), new String(bytes));
            log.info("Avro deserializer read byte array with length: {}", bytes.length);
            return reader.read(null, decoder);
        } catch (IOException e) {
            log.error("Deserialization IO error.", e);
        }
        return null;
    }

    @Override
    public Message deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
