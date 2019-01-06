package com.invoice.utilities;

import com.invoice.application.Driver;
import com.invoice.view.AlertUser;
import com.invoice.view.DatabaseEntryView;
import com.invoice.view.DefaultActivityView;
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
            if (isCancelled()) {
                return i;
            }
        }
        return i;
    }

    @Override
    protected void failed() {
        System.out.println("fail");
    }


    @Override
    protected void updateProgress(long workDone, long max) {
        updateMessage("Task complete.");
        super.updateProgress(workDone, max);
    }

    @Override
    protected void succeeded() {
        DatabaseEntryView et = new DatabaseEntryView();
        System.out.println("complete");

        AlertUser popup = new AlertUser();
        indicator.progressProperty().bind(bar.progressProperty());
        applicationDriver.getStage().setScene(popup.ShowRecords(applicationDriver.getStage(), applicationDriver.switchScene(activityView.createContent()), "Task Completed", "Files successfully downloaded to directory."));
        applicationDriver.getStage().getScene().getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
        applicationDriver.getStage().show();
        bar.progressProperty().unbind();
        indicator.progressProperty().unbind();

    }


    public boolean cancel(boolean mayInterruptedIfRunning) {
        updateMessage("task failed");
        System.out.println("cancelled");
        bar.progressProperty().bind(progressProperty());
        return super.cancel(mayInterruptedIfRunning);
    }
}

