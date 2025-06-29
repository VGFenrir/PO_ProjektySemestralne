module org.example.breakouttemplate {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.breakouttemplate to javafx.fxml;
    exports org.example.breakouttemplate;
}