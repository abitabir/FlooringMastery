/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.flooringmasteryproject.view;

import com.mthree.flooringmasteryproject.model.Order;
import com.mthree.flooringmasteryproject.service.OrderService;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author khali
 */

public class OrderView {
    private final Scanner scanner = new Scanner(System.in);
    
    public String promptForInput(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public void displayOrders(ArrayList<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("Error: No orders found.");
        } else {
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

    public void displayError(String message) {
        System.out.println("Error: " + message);
    }

    public void displaySuccess(String message) {
        System.out.println("Success: " + message);
    }

    public void showMainMenu() {
        System.out.println("1. Display Orders");
        System.out.println("2. Add an Order");
        System.out.println("3. Edit an Order");
        System.out.println("4. Remove an Order");
        System.out.println("5. Export All Data");
        System.out.println("6. Quit");
    }
}