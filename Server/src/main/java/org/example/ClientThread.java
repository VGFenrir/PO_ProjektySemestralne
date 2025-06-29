package org.example;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread{
    private Socket socket;
    private Server server;
    private PrintWriter writer;
    private String clientName;

    public ClientThread(Socket socket, Server server){
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run(){
        try {
            System.out.println("STARTING CLIENT HANDLER THREAD");
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            this.writer = new PrintWriter(outputStream,true);
            String message;
            while((message = reader.readLine()) != null){
                String prefix = message.substring(0,2);
                String postfix = message.substring(2);
                switch(prefix) {
                    case "LO" -> login(postfix);
                    case "BR" -> server.broadcast(this,postfix);
                    case "WH" -> server.whisper(this,postfix);
                    case "ON" -> server.online(this);
                }
            }
        }
        catch(SocketException e){
            server.broadcastLogout(this);
        }
        catch (IOException e) {
            e.fillInStackTrace();
        }
    }
    public void send(String message){
        writer.println(message);
    }
    public String getClientName(){
        return clientName;
    }
    public void login(String name) {
        clientName = name;
        server.online(this);
        server.broadcastLogin(this);
    }

}
