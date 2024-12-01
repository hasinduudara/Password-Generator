module com.hasindutech.opensource.passwordgenerator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.hasindutech.opensource.passwordgenerator to javafx.fxml;
    exports com.hasindutech.opensource.passwordgenerator;
}