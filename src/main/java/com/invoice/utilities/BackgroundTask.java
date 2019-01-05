package com.invoice.utilities;

import com.invoice.application.Driver;
import com.invoice.view.AlertUser;
import com.invoice.view.DefaultActivityView;
import com.invoice.view.ReportActivityView;
import com.jfoenix.controls.JFXProgressBar;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressIndicator;


/*
 * implement this class to handle all background task
 * to prevent the ui thread from freezing up
 *
 */
public class BackgroundTask extends Task<Double> {
    private JFXProgressBar bar;
    private ProgressIndicator indicator;
    private Driver applicationDriver;
    private DefaultActivityView activityView;
    private ReportActivityView view;


    public BackgroundTask(JFXProgressBar bar, ProgressIndicator indicator, Driver applicationDriver, DefaultActivityView activityView) {
        this.bar = bar;
        this.indicator = indicator;
        this.applicationDriver = applicationDriver;
        this.activityView = activityView;

    }

    @Override
    protected Double call() throws Exception {
        double i;
        for (i = 0; i < 1000; i++) {
            updateProgress(i, 1000);
            Thread.sleep(10);
        }
        return i;
    }

    @Override
    protected void failed() {
        System.out.println("fail");
    }

    @Override
    protected void succeeded() {

        System.out.println("complete");
        AlertUser popup = new AlertUser(bar, indicator);
        indicator.progressProperty().bind(bar.progressProperty());
        applicationDriver.getStage().setScene(popup.popupDialog(applicationDriver.getStage(), applicationDriver.switchScene(activityView.createContent()), "Task Completed", "Files downloaded to directory."));
        applicationDriver.getStage().getScene().getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
        applicationDriver.getStage().show();

    }


}

