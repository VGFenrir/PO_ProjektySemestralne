package org.example;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread{
    private Socket socket;
    private PrintWriter writer;

    public ClientThread(int port){
        try{
            this.socket = new Socket("localhost", port);
        }
        catch(IOException e){
            e.fillInStackTrace();
        }
    }

    @Override
    public void run(){
        try {
            System.out.println("CLIENT THREAD STARTING\n");
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            this.writer = new PrintWriter(outputStream, true);
            String message;
            while((message = reader.readLine()) != null){
                System.out.println("CLIENT THREAD "+message);
            }
        }
        catch(IOException e){
            e.fillInStackTrace();
        }
    }

    private void send(String message){
        writer.println(message);
    }
    public void login(String name){
        writer.println("LO "+name);
    }
    public void broadcast(String message){
        writer.println("BR "+message);
    }
    public void online(){
        writer.println("ON ");
    }
    public void whisper(String message){
        writer.println("WH "+message);
    }

}
