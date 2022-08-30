module com.example.gamesclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gamesclient to javafx.fxml;
    exports com.example.gamesclient;
}