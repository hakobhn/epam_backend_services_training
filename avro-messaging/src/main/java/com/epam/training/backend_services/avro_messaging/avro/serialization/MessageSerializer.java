package com.epam.training.backend_services.avro_messaging.avro.serialization;

import com.epam.training.backend_services.avro_messaging.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class MessageSerializer implements Serializer<Message> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String topic, Message message) {
        DatumWriter<Message> writer = new SpecificDatumWriter<>(Message.class);
        byte[] data = new byte[0];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Encoder jsonEncoder = null;
        try {
            jsonEncoder = EncoderFactory.get()
                    .jsonEncoder(Message.getClassSchema(), stream);
            writer.write(message, jsonEncoder);
            jsonEncoder.flush();
            data = stream.toByteArray();
            log.info("Avro serializer serialized message {} to byte array with length: {}", message.getId(), data.length);
        } catch (IOException e) {
            log.error("Serialization IO error.", e);
        }
        return data;
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Message data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }

}
