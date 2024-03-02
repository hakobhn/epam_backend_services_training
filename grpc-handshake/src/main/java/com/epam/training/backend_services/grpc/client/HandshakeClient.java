package com.epam.training.backend_services.grpc.client;

import com.epam.training.backend_services.grpc.Greeting;
import com.epam.training.backend_services.grpc.GreetingReply;
import com.epam.training.backend_services.grpc.GreetingRequest;
import com.epam.training.backend_services.grpc.HandshakeServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

import static com.epam.training.backend_services.grpc.Configuration.PORT;

@Slf4j
public class HandshakeClient {

    public static void main(String[] args) {

        // Channel is used by the client to communicate with the server using the domain localhost and port 5003.
        // This is the port where our server is starting.
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", PORT)
                .usePlaintext()
                .build();

        // Auto generated stub class with the constructor wrapping the channel.
        HandshakeServiceGrpc.HandshakeServiceBlockingStub stub = HandshakeServiceGrpc.newBlockingStub(channel);

        // Start calling the `handshake` method
        GreetingRequest hiRequest = GreetingRequest.newBuilder()
                .setGreeting(Greeting.newBuilder()
                        .setPhrase("Hi")
                        .setAction("WAVE")
                        .build())
                .build();

        GreetingReply reply = stub.handshake(hiRequest);
        log.info("Reply for the first call phrase: {} and info {}", reply.getPhrase(), reply.getInfo());

        // Call to the `handshake` method is successfully completed. Now calling the `handshakeManyTimes` method.
        // Hold the thread for 10s before calling the other method.
        log.info("### Initiating the second request ###");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            log.error("Caught error on thread sleep.", e);
        }

        GreetingRequest helloRequest = GreetingRequest.newBuilder()
                .setGreeting(Greeting.newBuilder()
                        .setPhrase("Hello")
                        .setAction("SMILE")
                        .build())
                .build();

        log.info("Reply for the second call: ");
        stub.handshakeManyTimes(helloRequest).forEachRemaining(
                handshakeManyTimes -> {
            log.info("Reply for the call phrase: {} and info {}", handshakeManyTimes.getPhrase(), handshakeManyTimes.getInfo());
        });
        // Call to the `handshakeManyTimes` method is successfully completed.
    }
}
