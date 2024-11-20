/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.flooringmasteryproject.view;

import com.mthree.flooringmasteryproject.model.Order;
import com.mthree.flooringmasteryproject.service.OrderService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author khali
 */
public class OrderView {
    
    private OrderService orderService;
    private Scanner scanner = new Scanner(System.in);
    private boolean run = true;
    

    public OrderView(OrderService orderService) {
        this.orderService = orderService;
    }

    public void showMainMenu() {
        
        while (run) {
            System.out.println("1. Display Orders");
            System.out.println("2. Add an Order");
            System.out.println("3. Edit an Order");
            System.out.println("4. Remove an Order");
            System.out.println("5. Export All Data");
            System.out.println("6. Quit");
            String choice = promptForInput("Choose an option: ");

            switch (choice) {
                case "1":
                    displayOrders();
                    break;
                case "2":
                    addOrder();
                    break;
                case "3":
                    editOrder();
                    break;
                case "4":
                    removeOrder();
                    break;
                case "5":
                    exportData();
                    break;
                case "6":
                    run = false;
                default:
                    displayError("Invalid option. Please try again.");
            }
        }
    }

    public String promptForInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private void displayOrders() {
        LocalDate date = LocalDate.parse(promptForInput("Enter order date (yyyy-mm-dd): "));
        ArrayList<Order> orders = orderService.getOrdersByDate(date);
        displayOrders(orders);
    }
    
    public void displayOrders(ArrayList<Order> orders) {
        if (orders.isEmpty()) {
            displayError("No orders found for this date.");
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

    private void addOrder() {
        LocalDate date = LocalDate.parse(promptForInput("Enter order date (yyyy-mm-dd): "));
        String customerName = promptForInput("Enter customer name: ").strip();
        if (!orderService.validateCustomerName(customerName)) {
            displayError("Invalid customer name. Please try again.");
            return;
        }

        String state = promptForInput("Enter state: ").strip();
        if (!orderService.validateState(state)) {
            displayError("Invalid state. Please try again.");
            return;
        }

        String productType = promptForInput("Enter product type: ").strip();
        if (!orderService.validateProductType(state)) {
            displayError("Invalid state. Please try again.");
            return;
        }

        String areaInput = promptForInput("Enter area (min 100 sq ft): ").strip();
        if (!orderService.validateArea(areaInput)) {
            displayError("Area must be a positive decimal and at least 100 sq ft.");
            return;
        }
        this.orderService.addOrder(customerName, state, productType, areaInput, date);
    }

    private void editOrder() {
        // Prompt for order number and date, validate, fetch, and allow editing
    }

    private void removeOrder() {
        // Prompt for order number and date, confirm deletion
    }

    private void exportData() {
        System.out.println("This functionality is currently unavailable.");
    }
}
