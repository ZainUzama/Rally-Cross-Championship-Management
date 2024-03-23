module com.example.cw1601 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cw1601 to javafx.fxml;
    exports com.example.cw1601;
}