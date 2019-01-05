package com.invoice.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXProgressBar;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/* Author: Shavar Litchmore
 *Date: 01-02-19
 * Reusable class to alert/popup information to the user
 */
public class AlertUser {

    private JFXProgressBar bar;
    private ProgressIndicator indicator;

    public AlertUser(JFXProgressBar bar, ProgressIndicator indicator) {
        this.bar = bar;
        this.indicator = indicator;
    }

    public Scene popupDialog(Stage stage, Scene mainView, String title, String message) {

        StackPane stackpane = new StackPane();
        stackpane.setStyle("-fx-background-color:#202a34");
        Scene scene = new Scene(stackpane, 600, 400);
        scene.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
        HBox box = new HBox(20);
        Label support = new Label(message);
        support.setTranslateX(80);
        JFXDialogLayout content = new JFXDialogLayout();
        Label header = new Label(title);
        header.setTranslateX(170);
        content.setHeading(header);
        content.setPrefSize(500, 250);
        bar.setTranslateY(75);
        bar.setTranslateX(75);
        indicator.setTranslateY(90);
        indicator.setTranslateX(300);

        content.setBody(support, bar, indicator);
        content.setStyle("-fx-background-color:#202a34;");
        JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.BOTTOM, false);

        JFXButton close = new JFXButton("Dismiss");


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
