package org.example.chatclient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextArea chatTextArea;
    @FXML
    private TextField messageTextField;
    @FXML
    private Button messageSendButton;
    @FXML
    private  Button loginButton;
    @FXML
    private ListView onlineUsersList;

    private ClientThread clientThread;
    private ObservableList<String> onlineClients;

    public void setClientThread(ClientThread clientThread){
        this.clientThread = clientThread;
    }

    @FXML
    protected void onSendButtonClick(){
        //chatTextArea.appendText(messageTextField.getText()+"\n");
        if(!Objects.equals(messageTextField.getText(), "")) {
            clientThread.broadcast(messageTextField.getText());
            messageTextField.clear();
        }
    }

    @FXML
    protected void onLoginButtonClick(){
        clientThread.login(messageTextField.getText());
        messageTextField.clear();
        loginButton.setDisable(true);
        loginButton.setVisible(false);
        messageSendButton.setDisable(false);
        messageSendButton.setVisible(true);
        chatTextArea.setDisable(false);
        onlineUsersList.setDisable(false);

        //clientThread.online();
    }

    @FXML
    protected void setOnlineUsers(String users){
        String[] data = users.split(" {2}");
        onlineClients = FXCollections.observableArrayList();
        onlineClients.addAll(data);
        onlineUsersList.setItems(onlineClients);
    }

    protected void addOnlineUser(String user){
        onlineClients.add(user);
        onlineUsersList.refresh();
    }

    @FXML
    protected void setMessageBroadcast(String message){
        chatTextArea.appendText(message+"\n");
    }

    @FXML
    protected void receiveMessage(String message){
        String[] data = message.split(" {2}",2);
        switch(data[0]){
            case "ON":
                this.setOnlineUsers(data[1]);
                break;
            case "BR":
                this.setMessageBroadcast(data[1]);
                break;
            case "WH":
                break;
            case "LN":
                this.addOnlineUser(data[1]);
                break;
            default:
                System.err.println("Malformed message prefix: "+data[0]);
        }
    }
}