package com.epam.training.backend_services.grpc.server;

import com.epam.training.backend_services.grpc.GreetingReply;
import com.epam.training.backend_services.grpc.GreetingReplyManyTimes;
import com.epam.training.backend_services.grpc.GreetingRequest;
import com.epam.training.backend_services.grpc.HandshakeServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandshakeServiceImpl extends HandshakeServiceGrpc.HandshakeServiceImplBase {

    @Override
    public void handshake(GreetingRequest request, StreamObserver<GreetingReply> responseObserver) {

        String phrase = request.getGreeting().getPhrase();
        String action = request.getGreeting().getAction();

        log.info("Got greeting {} and action {}.", phrase, action);
        GreetingReply reply = GreetingReply
                .newBuilder()
                .setPhrase("Hey. What's up?")
                .setInfo(String.format("Received greeting with message %s and action %s.", phrase, action))
                .build();

        // Send the response to the client.
        responseObserver.onNext(reply);

        // Notifies the customer that the call is completed.
        responseObserver.onCompleted();
        log.info("Replied to {}", reply.getInfo());

    }

    @Override
    public void handshakeManyTimes(GreetingRequest request, StreamObserver<GreetingReplyManyTimes> responseObserver) {

        String phrase = request.getGreeting().getPhrase();
        String action = request.getGreeting().getAction();
        log.info("Got greeting {} and action {}.", phrase, action);

        // Build and send the first response.
        GreetingReplyManyTimes reply1 = GreetingReplyManyTimes
                .newBuilder()
                .setPhrase("Hi there!")
                .setInfo(String.format("Received greeting with message %s and action %s.", phrase, action))
                .build();
        log.info("Build and send the first response");
        responseObserver.onNext(reply1);

        // Hold the thread for 5s before sending the second response.
        try {
            Thread.sleep(000);
        } catch (InterruptedException e) {
            log.error("Caught error on thread sleep.", e);
        }

        // Build and send the second response.
        GreetingReplyManyTimes reply2 = GreetingReplyManyTimes
                .newBuilder()
                .setPhrase("Hey there!")
                .setInfo(String.format("Received greeting with message %s and action %s.", phrase, action))
                .build();
        log.info("Build and send the second response");
        responseObserver.onNext(reply2);

        // Hold the thread for 5s before sending the third response.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error("Caught error on thread sleep.", e);
        }

        // Build and send the second response.
        GreetingReplyManyTimes reply3 = GreetingReplyManyTimes
                .newBuilder()
                .setPhrase("What's up!")
                .setInfo(String.format("Received greeting with message %s and action %s.", phrase, action))
                .build();
        log.info("Build and send the third response");
        responseObserver.onNext(reply3);

        // Complete the communication.
        responseObserver.onCompleted();

        log.info("Finished handshake!");
    }
}
