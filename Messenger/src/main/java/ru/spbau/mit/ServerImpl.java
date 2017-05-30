package ru.spbau.mit;

import io.grpc.stub.StreamObserver;

import java.util.LinkedHashSet;
import java.util.logging.Logger;


public class ServerImpl extends ChatServiceGrpc.ChatServiceImplBase {
    private static final Logger logger = Logger.getLogger(ServerImpl.class.getName());

    private static LinkedHashSet<StreamObserver<Chat.ChatMessageFromServer>> observers = new
            LinkedHashSet<StreamObserver<Chat.ChatMessageFromServer>>();

    @Override
    public StreamObserver<Chat.ChatMessage> chat(final StreamObserver<Chat.ChatMessageFromServer> responseObserver) {
        observers.add(responseObserver);

        return new StreamObserver<Chat.ChatMessage>() {
            @Override
            public void onNext(Chat.ChatMessage value) {
                logger.info(value.getFrom() + ": " + value.getMessage());
                Chat.ChatMessageFromServer message = Chat.ChatMessageFromServer
                        .newBuilder()
                        .setMessage(value)
                        .build();
                try {
                    observers.stream().forEach(observer -> observer.onNext(message));
                } catch (Exception ex) {
                }
            }

            @Override
            public void onError(Throwable t) {
            }

            @Override
            public void onCompleted() {
                observers.remove(responseObserver);
            }
        };
    }
}
