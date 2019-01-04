package com.invoice.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Author: Shavar litchmore
 * This class will represent our report scene
 * when the user clicks the report button
 * from the default(initial screen) they will be taken here.
 */
public class ReportActivityView {
    private JFXDatePicker datepicker;
    private JFXTextField fetchInvoice;
    private JFXButton fetchFromDB = new JFXButton("Fetch Invoice");
    private JFXButton home = new JFXButton("home");

    public Parent createReportContent() {
        BorderPane pane = new BorderPane();

        VBox root = new VBox(30);
        HBox hbox = new HBox(20, fetchFromDB, home);

        hbox.setAlignment(Pos.CENTER);
        datepicker = new JFXDatePicker();
        datepicker.setDefaultColor(Color.valueOf("#3f51b5"));
        fetchInvoice = new JFXTextField();
        fetchInvoice.setMaxWidth(180);
        fetchInvoice.setTranslateX(-25);
        fetchInvoice.setPromptText("Enter Invoice Number.");
        root.setPrefSize(600, 400);
        root.setPadding(new Insets(25));
        root.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(new Label("To view your invoice please select a date below and \n\tprovide an invoice number to continue."), datepicker, fetchInvoice, hbox);
        pane.setCenter(root);
        return pane;
    }

    public LocalDate getDate() {
        LocalDate localDate = datepicker.getValue();
        /*
         * the code below is for different timezones
         */
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);

        return localDate;

    }

    public String getInovice() {

        return fetchInvoice.getText();

    }


    public void ClickEvent(EventHandler<ActionEvent> eventhandler) {
        //on click need to navigate back to home screen . the report will be fetched and displayed separately
        fetchFromDB.setOnAction(eventhandler);
    }

    public void goHome(EventHandler<ActionEvent> eventhandler) {
        //on click need to navigate back to home screen . the report will be fetched and displayed separately
        home.setOnAction(eventhandler);
    }


}
