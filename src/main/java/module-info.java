module org.example.assignment12 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.assignment12 to javafx.fxml;
    exports org.example.assignment12;
}