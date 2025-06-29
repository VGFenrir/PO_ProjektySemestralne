package org.example;

import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Main{
    public static void main(String[] args){
        Server server = new Server(3000);
        server.start();
    }
}