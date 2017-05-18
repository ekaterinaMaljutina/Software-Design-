package ru.spbau.mit;

import io.grpc.ServerBuilder;

import java.io.IOException;


public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        io.grpc.Server server = ServerBuilder.forPort(Integer.parseInt(args[0])).addService(
                new ServerImpl()).build();
        System.out.println(args[0]);
        server.start();
        server.awaitTermination();
    }
}
