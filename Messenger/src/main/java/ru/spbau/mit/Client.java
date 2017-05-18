package ru.spbau.mit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Scanner;

public class Client {

    private static String name;
    private static String host;
    private static int port;
    private static ManagedChannel channel;
    private static ChatServiceGrpc.ChatServiceStub chatService;
    private static StreamObserver<Chat.ChatMessage> chat;

    private static void send(final String msg) throws InterruptedException {
        chat.onNext(Chat.ChatMessage.newBuilder().setFrom(name)
                .setData(Time.getCurrentData())
                .setMessage(msg).build());
    }

    public static void main(String[] args) {
        try {
            host = args[0];
            port = Integer.parseInt(args[1]);
            name = args[2];
            channel = ManagedChannelBuilder.forAddress(host, port)
                    .usePlaintext(true)
                    .build();
            chatService = ChatServiceGrpc.newStub(channel);
            chat = chatService.chat(new StreamObserver<Chat.ChatMessageFromServer>() {
                @Override
                public void onNext(Chat.ChatMessageFromServer value) {
                    System.out.println(
                            value.getMessage().getData() + "\t" +
                            value.getMessage().getFrom() + ":\t" + value.getMessage().getMessage());
                }

                @Override
                public void onError(Throwable t) {
                    System.out.println("Disconnected");
                    System.exit(1);
                }

                @Override
                public void onCompleted() {
                    System.out.println("Disconnected");
                    System.exit(1);
                }
            });
            while (true) {
                Scanner in = new Scanner(System.in);
                send(in.nextLine());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
