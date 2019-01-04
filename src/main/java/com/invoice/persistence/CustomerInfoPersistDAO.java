package com.invoice.persistence;

import com.invoice.model.CustomerModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerInfoPersistDAO {

    int persistCustomer(CustomerModel customer) {
        int executeUpdate = 0;
        Connection connection = DatabaseHandler.getConnection();
        try {
            PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO CUSTOMER_INFO VALUES (?,?,?)");
            prepareStatement.setInt(1, customer.getUniqueId());
            prepareStatement.setString(2, customer.getName());
            prepareStatement.setString(3, customer.getEmail());

            executeUpdate = prepareStatement.executeUpdate();


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return executeUpdate;

    }
}
