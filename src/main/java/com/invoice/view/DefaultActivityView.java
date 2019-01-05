package com.invoice.view;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class DefaultActivityView {

    private JFXButton showReports = new JFXButton("New Report");
    private JFXButton createEntry = new JFXButton("New Entry");

    public Parent createContent() {
        Label welcomeUser = new Label("Welcome To Invoice Management");
        welcomeUser.setAlignment(Pos.CENTER);
        welcomeUser.setTranslateY(20);
        welcomeUser.setTranslateX(170);
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(50));
        hbox.getChildren().addAll(showReports, createEntry);
        BorderPane borderpane = new BorderPane();


        borderpane.setTop(welcomeUser);
        borderpane.setBottom(hbox);
        return borderpane;

    }

    /**
     * @param eventHandler Use this method to access our buttons from the controller class
     *                     separate the logic from the view for maintainability.
     *                     in the controller class implement these methods to
     *                     perform a an action.
     */
    public void reportHandler(EventHandler<ActionEvent> eventHandler) {
        showReports.setOnAction(eventHandler);

    }

    public void entryHandler(EventHandler<ActionEvent> eventHandler) {
        createEntry.setOnAction(eventHandler);

    }


}
