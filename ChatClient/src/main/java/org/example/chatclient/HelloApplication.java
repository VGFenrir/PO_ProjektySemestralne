package org.example.chatclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    HelloController controller;
    ClientThread clientThread;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);

        controller = fxmlLoader.getController();

        clientThread = new ClientThread(3000);
        clientThread.setController(controller);
        clientThread.start();
        controller.setClientThread(clientThread);

        scene.setOnKeyPressed(e ->{
            if(e.getCode() == KeyCode.ENTER){
                controller.onSendButtonClick();
            }
        });

        stage.show();
    }

    public ClientThread getClientThread() {
        return clientThread;
    }

    public static void main(String[] args) {
        launch();
    }
}