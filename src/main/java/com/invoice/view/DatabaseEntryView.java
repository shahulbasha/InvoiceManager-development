package com.invoice.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

/*

 * Entry view where the customer  will be able to create a new entry for selected services
 * once the entry is filed and validated  the data is then
 *persist to the database
 * compose jaser report
 *
 * -->needs to implement the services that will be applicable to finish this scene.
 */
public class DatabaseEntryView {
    private JFXTextField customerName;
    private JFXTextField customerID;
    private JFXTextField customerEmail;
    private JFXTextField customerPhone;
    private JFXButton submit = new JFXButton("Submit Entry");
    private JFXButton home = new JFXButton("Home");

    public Parent displayEntryView() {
        BorderPane root = new BorderPane();
        root.setPrefSize(600, 400);
        VBox vbox = new VBox(25);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        HBox hbox = new HBox(25);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20));
        hbox.getChildren().addAll(submit, home);
        customerName = new JFXTextField();
        customerName.setPromptText("Customer Name");
        customerName.setMaxWidth(300);
        customerPhone = new JFXTextField();
        customerPhone.setPromptText("Mobile Number");
        customerPhone.setMaxWidth(300);
        customerID = new JFXTextField();
        customerID.setPromptText("Customer ID");
        customerID.setMaxWidth(300);
        customerEmail = new JFXTextField();
        customerEmail.setPromptText("Customer Email");
        customerEmail.setMaxWidth((300));


        vbox.getChildren().addAll(new Label("Create New Entry"), customerName, customerPhone, customerEmail, customerID, hbox);
        root.setTop(vbox);
        return root;
    }

    public String getCustomerName() {
        return customerName.getText();
    }

    public String customerEmail() {
        return customerEmail.getText();

    }

    public String customerId() {
        return customerID.getText();
    }

    public String customerPhone() {

        return customerPhone.getText();
    }

    public void changeValidationColors(Paint paint) {
        customerName.setUnFocusColor(paint);
        customerID.setUnFocusColor(paint);
        customerPhone.setUnFocusColor(paint);
        customerEmail.setUnFocusColor(paint);
    }

    public void validateTextFields() {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        customerName.getValidators().add(validator);
        customerEmail.getValidators().add(validator);
        customerPhone.getValidators().add(validator);
        customerID.getValidators().add(validator);
        validator.setMessage("No input given");
        customerName.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                customerName.validate();

            }
        });
        customerEmail.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                customerEmail.validate();

            }
        });
        customerPhone.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                customerPhone.validate();

            }
        });
        customerID.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                customerID.validate();

            }
        });
    }


    public void entryHandler(EventHandler<ActionEvent> eventHandler) {
        //shahul
        //first validate the customer details using regex as soon as submit button is clicked
        //set customer info from UI to customer model
        //pass it to customer Info persist DAO method
        //If you receive response 1 from that method show successfully saved dialog
        //try to simulate the DB response if you face some issue
        submit.setOnAction(eventHandler);

    }

    public JFXButton getbutton() {

        return submit;
    }

    public void homeHandler(EventHandler<ActionEvent> eventHandler) {
        //shahul
        //first validate the customer details using regex as soon as submit button is clicked
        //set customer info from UI to customer model
        //pass it to customer Info persist DAO method
        //If you receive response 1 from that method show successfully saved dialog
        //try to simulate the DB response if you face some issue
        home.setOnAction(eventHandler);

    }
}




