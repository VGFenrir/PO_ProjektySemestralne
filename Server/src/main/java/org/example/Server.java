package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Server extends Thread{
    private final List<ClientThread> onlineClients = new ArrayList<>();
    private ServerSocket serverSocket;
    private Socket clientSocket;

    public Server(int port){
        try {
            this.serverSocket = new ServerSocket(port);
        }
        catch(IOException e){
            e.fillInStackTrace();
        }
    }

    @Override
    public void run(){
        System.out.println("SERVER: Listening...");
        while(true){
            try {
                this.clientSocket = serverSocket.accept();
                System.out.println("SERVER: A new client has connected.");
                this.onlineClients.add(new ClientThread(this.clientSocket,this));
                this.onlineClients.getLast().start();
            }
            catch(IOException e){
                System.err.println(e.getMessage());
            }
        }
    }

    public void removeClient(ClientThread client) {
        onlineClients.remove(client);
        broadcastLogout(client);
    }

    public void broadcast(ClientThread sender, String message){
        for(var currentClient : onlineClients)
            currentClient.send("BR "+sender.getClientName()+": "+message);

    }

    public void broadcastLogin(ClientThread client) {
        for(var currentClient : onlineClients)
            if(currentClient != client)
                currentClient.send("LN "+client.getClientName());

    }

    public void broadcastLogout(ClientThread client) {
        for(var currentClient : onlineClients)
            if(currentClient != client) {
                currentClient.send("LT " + client.getClientName());
                synchronized (onlineClients){
                    onlineClients.remove(client);
                }
            }
    }
    public void online(ClientThread sender) {
        String listString = onlineClients.stream()
                .map(ClientThread::getClientName)
                .collect(Collectors.joining(" "));
        sender.send("ON "+listString);
    }

    private Optional<ClientThread> getClient(String clientName) {
        return onlineClients.stream()
                .filter(client -> clientName.equals(client.getClientName()))
                .findFirst();
    }

    public void whisper(ClientThread sender, String message) {
        String[] messageArr = message.split(" ");
        String recipientName = messageArr[0];

        Optional<ClientThread> recipient = getClient(recipientName);
        if(recipient.isPresent()) {
            recipient.get().send("WH "+sender.getClientName()+" "+messageArr[1]);
        }
        else sender.send("NU "+recipientName);
    }
}
