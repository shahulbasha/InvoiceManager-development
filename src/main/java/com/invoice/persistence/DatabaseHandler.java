package com.invoice.persistence;

import com.invoice.utilities.PropertiesHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseHandler {

    static DatabaseHandler handler = null;

    private DatabaseHandler() {
        try {
            Class.forName(PropertiesHandler.getPropertiesData("driver")).newInstance();
            //	Connection conn=DriverManager.getConnection(PropertiesHandler.getPropertiesData("URL"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DatabaseHandler getInstance() {
        return handler == null ? new DatabaseHandler() : handler;
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            //	Class.forName(PropertiesHandler.getPropertiesData("driver")).newInstance();
            conn = DriverManager.getConnection(PropertiesHandler.getPropertiesData("URL"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;


    }


}
