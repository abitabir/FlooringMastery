/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mthree.flooringmasteryproject;

import com.mthree.flooringmasteryproject.controller.OrderController;
import com.mthree.flooringmasteryproject.dao.DatabaseSetup;
import com.mthree.flooringmasteryproject.dao.MySQLLocalDatabaseConnection;
import com.mthree.flooringmasteryproject.dao.OrderDAO;
import com.mthree.flooringmasteryproject.dao.ProductDAO;
import com.mthree.flooringmasteryproject.dao.TaxDAO;
import com.mthree.flooringmasteryproject.service.OrderService;
import com.mthree.flooringmasteryproject.view.OrderView;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author khali
 */

public class FlooringMasteryProject {
    public static void main(String[] args) {
        try {
            // Get connection
            // Ensure database exists before connecting
            MySQLLocalDatabaseConnection.ensureDatabaseExists();
            Connection connection = MySQLLocalDatabaseConnection.getConnection();

            // Set up database and tables
            DatabaseSetup dbSetup = new DatabaseSetup();
            dbSetup.createDatabase("flooring");
            dbSetup.createTable("ORDERS");
            dbSetup.createTable("PRODUCTS");
            dbSetup.createTable("TAXES");

            // Insert default records
            dbSetup.insertDefaultRecords("ORDERS");
            dbSetup.insertDefaultRecords("PRODUCTS");
            dbSetup.insertDefaultRecords("TAXES");

            System.out.println("Database setup complete.");

            // Initialise services and controllers
            OrderService orderService = new OrderService(new OrderDAO(), new ProductDAO(), new TaxDAO(), connection);
            OrderView orderView = new OrderView();
            OrderController orderController = new OrderController(orderService, orderView);

            // Run application
            orderController.showMainMenu();
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        } finally {
            MySQLLocalDatabaseConnection.closeConnection();
        }
    }
}
