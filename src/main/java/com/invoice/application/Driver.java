package com.invoice.application;

import com.invoice.controller.SceneController;
import com.invoice.utilities.RegexPatternMatch;
import com.invoice.view.DatabaseEntryView;
import com.invoice.view.DefaultActivityView;
import com.invoice.view.ReportActivityView;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Driver extends Application {

    private Stage stage = new Stage();
    Scene defaultView;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        DefaultActivityView mainActivity = new DefaultActivityView();
        ReportActivityView reportActivityView = new ReportActivityView();
        DatabaseEntryView entryView = new DatabaseEntryView();
        RegexPatternMatch pattern = new RegexPatternMatch();
        SceneController controller = new SceneController(reportActivityView, entryView, this, mainActivity, pattern);

        stage.setTitle("Invoice Management");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        defaultView = new Scene(mainActivity.createContent(), 600, 400);
        defaultView.setFill(Color.valueOf("#202a34"));



        defaultView.getStylesheets().add(this.getClass().getResource("application.css").toExternalForm());
        stage.setScene(defaultView);
        stage.show();


    }

    public Scene switchScene(Parent parent) {
        return new Scene(parent, 600, 400);
    }


    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {


        return stage.getScene();

    }


}
