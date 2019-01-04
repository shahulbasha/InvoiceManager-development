package com.invoice.controller;

import com.invoice.application.Driver;
import com.invoice.utilities.RegexPatternMatch;
import com.invoice.view.DatabaseEntryView;
import com.invoice.view.DefaultActivityView;
import com.invoice.view.ReportActivityView;
import com.invoice.view.ValidateCredentials;
import javafx.scene.paint.Color;


public class SceneController {

    private ReportActivityView report;
    private DatabaseEntryView validateCustomerData;
    private Driver Applicationdriver;
    private DefaultActivityView mainScene;
    private RegexPatternMatch pattern;
    private ValidateCredentials popup = new ValidateCredentials();


    public SceneController(ReportActivityView report, DatabaseEntryView validateCustomerData, Driver applicationDriver, DefaultActivityView defaultActivityView, RegexPatternMatch pattern) {
        this.report = report;
        this.validateCustomerData = validateCustomerData;
        this.Applicationdriver = applicationDriver;
        this.mainScene = defaultActivityView;
        this.pattern = pattern;

        validateCustomerData.entryHandler(Event -> {
            Boolean verify = true;
            /*
             *when the submit button is click we verify the user data before persisting to the db
             * if valid the textfield unfocused color will change to green and vice versa it will change to red if not valid .
             * if all credentials are valid the popupDialog method invokes a alert to the user.
             */

            if (pattern.isValidCredentials(validateCustomerData, validateCustomerData.getCustomerName(), validateCustomerData.customerEmail(), validateCustomerData.customerPhone())) {
                applicationDriver.getStage().setScene(popup.popupDialog(applicationDriver.getStage(), applicationDriver.switchScene(validateCustomerData.displayEntryView()), "Compose Report", "request was successful composing report."));
                validateCustomerData.getbutton().setText("Entry SuccessFul");
                validateCustomerData.getbutton().setStyle("-fx-background-color:green;-fx-text-fill:white;");
                validateCustomerData.changeValidationColors(Color.GREEN);

            } else {
                validateCustomerData.changeValidationColors(Color.RED);
                validateCustomerData.validateTextFields();
                validateCustomerData.getbutton().setText("Entry Unsuccessful");
                validateCustomerData.getbutton().setStyle("-fx-background-color:red;-fx-text-fill:black;");
            }


        });

        defaultActivityView.reportHandler(changeView -> {


            applicationDriver.getStage().setScene(applicationDriver.switchScene(report.createReportContent()));
            applicationDriver.getStage().getScene().getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
            applicationDriver.getStage().show();
        });
        defaultActivityView.entryHandler(changeView -> {
            applicationDriver.getStage().setScene(applicationDriver.switchScene(validateCustomerData.displayEntryView()));
            applicationDriver.getStage().getScene().getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
            applicationDriver.getStage().show();
        });
        validateCustomerData.homeHandler(goHome -> {
            applicationDriver.getStage().setScene(applicationDriver.switchScene(defaultActivityView.createContent()));
            applicationDriver.getStage().getScene().getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
            applicationDriver.getStage().show();
        });
        report.goHome(goHome -> {
            applicationDriver.getStage().setScene(applicationDriver.switchScene(defaultActivityView.createContent()));
            applicationDriver.getStage().getScene().getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
            applicationDriver.getStage().show();
        });
        report.ClickEvent(retrieveReport -> {
            applicationDriver.getStage().setScene(popup.popupDialog(applicationDriver.getStage(),
                    applicationDriver.switchScene(mainScene.createContent()), "Fetch Request", "request was successful fetching invoice\nfrom database."));

        });
    }


}





