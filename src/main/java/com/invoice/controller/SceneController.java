package com.invoice.controller;

import com.invoice.application.Driver;
import com.invoice.utilities.BackgroundTask;
import com.invoice.utilities.RegexPatternMatch;
import com.invoice.view.AlertUser;
import com.invoice.view.DatabaseEntryView;
import com.invoice.view.DefaultActivityView;
import com.invoice.view.ReportActivityView;
import com.jfoenix.controls.JFXProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.paint.Color;


public class SceneController {

    private ReportActivityView report;
    private DatabaseEntryView validateCustomerData;
    private Driver Applicationdriver;
    private DefaultActivityView mainScene;
    private RegexPatternMatch pattern;
    private JFXProgressBar reportProgressBar = new JFXProgressBar();
    private ProgressIndicator progressIndicator = new ProgressIndicator();
    private JFXProgressBar entryProgressBar = new JFXProgressBar();
    private ProgressIndicator entryProgressIndicator = new ProgressIndicator();
    AlertUser user = new AlertUser();
    private AlertUser alertUser = new AlertUser(reportProgressBar, progressIndicator);
    private AlertUser displayDialog = new AlertUser(entryProgressBar, entryProgressIndicator);
    private BackgroundTask fetchReportTask;
    private BackgroundTask submitEntryTask;


    public SceneController(ReportActivityView report, DatabaseEntryView validateCustomerData, Driver applicationDriver, DefaultActivityView defaultActivityView, RegexPatternMatch pattern) {
        this.report = report;
        this.validateCustomerData = validateCustomerData;
        this.Applicationdriver = applicationDriver;
        this.mainScene = defaultActivityView;
        this.pattern = pattern;
        this.fetchReportTask = new BackgroundTask(reportProgressBar, progressIndicator, applicationDriver, defaultActivityView);
        this.submitEntryTask = new BackgroundTask(entryProgressBar, entryProgressIndicator, applicationDriver, defaultActivityView);
        validateCustomerData.entryHandler(Event -> {
            //runs background task  when the customer creates a new entry.
            submitCustomerEntryTask();
            /*
             *when the submit button is click we verify the user data before persisting to the db
             * if valid the text field unfocused color will change to green and vice versa it will change to red if not valid .
             * if all credentials are valid the popupDialog method invokes a alert to the user.
             */
                    validateCustomerData.print();
                    if (pattern.isValidCredentials(validateCustomerData, validateCustomerData.getCustomerName(), validateCustomerData.customerEmail(), validateCustomerData.customerPhone())) {
                        applicationDriver.getStage().setScene(displayDialog.popupDialog(applicationDriver.getStage(), applicationDriver.switchScene(validateCustomerData.displayEntryView()), "Compose Report", "request was successful composing report."));
                        validateCustomerData.getbutton().setText("Entry SuccessFul");
                        validateCustomerData.getbutton().setStyle("-fx-background-color:green;-fx-text-fill:white;");
                        validateCustomerData.changeValidationColors(Color.GREEN);

                    } else {
                        validateCustomerData.changeValidationColors(Color.RED);
                        validateCustomerData.validateTextFields();
                        validateCustomerData.getbutton().setText("Entry Unsuccessful");
                        validateCustomerData.getbutton().setStyle("-fx-background-color:red;-fx-text-fill:black;");
                    }


                }

        );

        defaultActivityView.reportHandler(changeView -> {
            /*
             *scene change
             */
            applicationDriver.getStage().setScene(applicationDriver.switchScene(report.createReportContent()));
            applicationDriver.getStage().getScene().getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
            applicationDriver.getStage().show();


        });


        defaultActivityView.recordHandler(showRecords -> {
            applicationDriver.getStage().setScene(user.ShowRecords(applicationDriver.getStage(),
                    applicationDriver.switchScene(defaultActivityView.createContent()), "Records File System", "All records are stored in our database and cannot be accessed here " +
                            "feature coming soon."));
        });


        defaultActivityView.entryHandler(changeView -> {
            /*
             *scene change
             */
            applicationDriver.getStage().setScene(applicationDriver.switchScene(validateCustomerData.displayEntryView()));
            applicationDriver.getStage().getScene().getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
            applicationDriver.getStage().show();
        });

        validateCustomerData.homeHandler(goHome -> {
            /*
             *scene change
             */
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
            /*
             * implement database validation to generate a jasper report
             * -> for now it runs a background task when the user clicks the
             * fetch invoice button and updates progress bar based on the task
             */
            FetchCustomerReportTask();
            applicationDriver.getStage().setScene(alertUser.popupDialog(applicationDriver.getStage(),
                    applicationDriver.switchScene(defaultActivityView.createContent()), "Task Status", "Downloading files to directory."));


        });

    }

    private void FetchCustomerReportTask() {
        /*
         background task for when the report fetch invoice button is clicked
         */
        Thread reportCustomerTask = new Thread(fetchReportTask);
        reportCustomerTask.setDaemon(true);
        reportCustomerTask.start();
        reportProgressBar.setPrefSize(220, 30);
        reportProgressBar.progressProperty().bind(fetchReportTask.progressProperty());

    }


    private void submitCustomerEntryTask() {
        /*
         background task for when the submit entry button is clicked
         */
        Thread entryBackgroundTask = new Thread(submitEntryTask);
        entryBackgroundTask.setDaemon(true);
        entryBackgroundTask.start();
        entryProgressBar.setPrefSize(220, 30);

        entryProgressBar.progressProperty().bind(submitEntryTask.progressProperty());


    }
}




