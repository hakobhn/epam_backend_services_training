package com.epam.training.backend_services.avro_messaging.avro;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;

public class AvroSchemaBuilder {

    public Schema createAvroHttpRequestSchema(){
        Schema message = SchemaBuilder.record("Message")
                .namespace("com.epam.training.backend_services.avro_messaging.model")
                .fields()
                .requiredInt("id")
                .requiredString("timestamp")
                .requiredString("payload")
                .endRecord();
        return message;
    }

}
