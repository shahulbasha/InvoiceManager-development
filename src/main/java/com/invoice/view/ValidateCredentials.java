package com.invoice.view;

import com.invoice.application.Driver;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/* Author: Shavar Litchmore
 *Date: 01-02-19
 * Reusable class to alert/popup information to the user
 */
public class ValidateCredentials {
    private Driver driver = new Driver();
    private DefaultActivityView activityView = new DefaultActivityView();

    public Scene popupDialog(Stage stage, Scene mainView, String title, String message) {

        StackPane stackpane = new StackPane();
        stackpane.setStyle("-fx-background-color:#202a34");
        Scene scene = new Scene(stackpane, 600, 400);
        scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
        Label support = new Label(message);
        support.setTranslateX(80);
        JFXDialogLayout content = new JFXDialogLayout();
        Label header = new Label(title);
        header.setTranslateX(170);
        content.setHeading(header);
        content.setPrefSize(500, 250);
        content.setBody(support);
        content.setStyle("-fx-background-color:#202a34;");
        JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.BOTTOM, false);

        JFXButton close = new JFXButton("Close");


        close.setOnAction(event -> {
            dialog.close();
            mainView.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
            stage.setScene(mainView);
        });
        content.setActions(close);
        dialog.show();
        return scene;
    }
}
