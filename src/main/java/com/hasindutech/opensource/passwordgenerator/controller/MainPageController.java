package com.hasindutech.opensource.passwordgenerator.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.util.Duration;

import java.security.SecureRandom;

public class MainPageController {

    @FXML
    private Button btnCopyPassword;

    @FXML
    private Button btnGeneratePassword;

    @FXML
    private Label lblPassword;

    @FXML
    private ProgressBar pbar;

    @FXML
    public void initialize() {
        pbar.setProgress(0.0);
        pbar.getStylesheets().add(getClass().getResource("/style/Style.css").toExternalForm());
    }

    @FXML
    void btnGeneratePasswordOnAction(ActionEvent event) {
        pbar.setProgress(0);
        Timeline timeline = new Timeline();
        for (int i = 0; i <= 20; i++) {
            double progress = i * 0.05;
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(i * 0.1), e -> pbar.setProgress(progress)));
        }
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(2.0), e -> {
            String password = generatePassword(10);
            lblPassword.setText(password);
            pbar.setProgress(1);
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

    private String generatePassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String specialChars = "@#&";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);
        password.append(specialChars.charAt(random.nextInt(specialChars.length())));
        for (int i = 1; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        for (int i = 0; i < password.length(); i++) {
            int randomIndex = random.nextInt(password.length());
            char temp = password.charAt(i);
            password.setCharAt(i, password.charAt(randomIndex));
            password.setCharAt(randomIndex, temp);
        }

        return password.toString();
    }

    @FXML
    void btnCopyPasswordOnAction(ActionEvent event) {
        String password = lblPassword.getText();
        if (!password.isEmpty()) {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(password);
            clipboard.setContent(content);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Password Copied");
            alert.setHeaderText(null);
            alert.setContentText("The password has been copied to the clipboard.");
            alert.showAndWait();
        }
    }

}
