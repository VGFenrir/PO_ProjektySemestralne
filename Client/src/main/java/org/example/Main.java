package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        ClientThread client = new ClientThread(3000);
        client.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Login: ");
        try {
            String line = reader.readLine();
            client.login(line);

            while(true){
                String command = reader.readLine();
                if(command.startsWith("/")){
                    String[] data = command.split(" ",2);
                    switch(data[0]){
                        case "online":
                            client.online();
                            break;
                        case "w":
                            client.whisper(data[1]);
                            break;
                        default:
                            System.out.println("Unknown command");
                            break;
                    }
                }
                else{
                    client.broadcast(command);
                }
            }
        }
        catch(IOException e){
            e.fillInStackTrace();
        }
    }
}