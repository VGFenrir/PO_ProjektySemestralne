module org.example.chatclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.chatclient to javafx.fxml;
    exports org.example.chatclient;
}