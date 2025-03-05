/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.flooringmasteryproject.controller;

import com.mthree.flooringmasteryproject.model.Order;
import com.mthree.flooringmasteryproject.service.OrderService;
import com.mthree.flooringmasteryproject.view.OrderView;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;

/**
 *
 * @author khali
 */

@Controller
public class OrderController {
    private final OrderService orderService;
    private final OrderView orderView;
    private boolean run = true;

    public OrderController(OrderService orderService, OrderView orderView) {
        this.orderService = orderService;
        this.orderView = orderView;
    }

    public void showMainMenu() throws SQLException {
        boolean run = true;
        while (run) {
            orderView.showMainMenu();
            String choice = orderView.promptForInput("Choose an option: ");
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
                    break;
                default:
                    orderView.displayError("Invalid option. Please try again.");
            }
        }
    }

    private void displayOrders() throws SQLException {
        LocalDate date = getValidOrderDate();
        ArrayList<Order> orders = orderService.getOrdersByDate(date);
        orderView.displayOrders(orders);
    }
    
    
    
    private LocalDate getValidOrderDate() {
        String orderDateInput;
        LocalDate orderDate;
        while (true) {
            orderDateInput = orderView.promptForInput("Enter order date (yyyy-mm-dd): ").strip();
            if (orderService.validateOrderDate(orderDateInput)) {
                return LocalDate.parse(orderDateInput);
            }
            orderView.displayError("Invalid order date. Please try again.");
        }
    }
    
    private Integer getValidOrderNumber() {
        String orderNumberInput;
        while (true) {
            orderNumberInput = orderView.promptForInput("Enter order number: ").strip();
            if (orderService.validateOrderNumber(orderNumberInput)) {
                return Integer.parseInt(orderNumberInput);
            }
            orderView.displayError("Invalid order number. Please try again.");
        }
    
    }

    private void addOrder() throws SQLException {
        LocalDate date = getValidOrderDate();
                
        String customerName;
        while (true) {
            customerName = orderService.toTitleCase(orderView.promptForInput("Enter customer name: ").strip());
            if (orderService.validateCustomerName(customerName)) {
                break;
            }
            orderView.displayError("Invalid customer name. Please try again.");
        }

        String state;
        while (true) {
            state = orderView.promptForInput("Enter state: ").strip().toUpperCase();
            if (orderService.validateState(state)) {
                break;
            }
            orderView.displayError("Invalid state. Please try again.");
        }

        String productType;
        while (true) {
            productType = orderService.toTitleCase(orderView.promptForInput("Enter product type: ").strip());
            if (orderService.validateProductType(productType)) {
                break;
            }
            orderView.displayError("Invalid product type. Please try again.");
        }

        String areaInput;
        while (true) {
            areaInput = orderView.promptForInput("Enter area (min 100 sq ft): ").strip();
            
            if (orderService.validateArea(areaInput)) {
                break;
            }
            orderView.displayError("Area must be a positive decimal number and at least 100 sq ft. Please try again.");
        }
        
        orderService.addOrder(customerName, state, productType, new BigDecimal(areaInput), date);
        orderView.displaySuccess("Order added successfully.");
    }

    private void editOrder() throws SQLException {
        // Prompt for order number and date, validate, fetch, and allow editing
        
        LocalDate orderDate = getValidOrderDate();

        int orderNumber = getValidOrderNumber();
        
        Order existingOrder = orderService.getOrderByNumberAndDate(orderNumber, orderDate);
        if (existingOrder == null) {
            orderView.displayError("Order not found.");
            return;
        }

        System.out.println("Editing order: " + existingOrder);
                
        String newCustomerName;
        while (true) {
            newCustomerName = orderService.toTitleCase(orderView.promptForInput("Enter new customer name (or press Enter to keep unchanged): ").strip());
            if ("".equals(newCustomerName)) {
                newCustomerName = existingOrder.getCustomerName();
                break;
            }
            else if (orderService.validateCustomerName(newCustomerName)) {
                break;
            }
            orderView.displayError("Invalid customer name. Please try again.");
        }

        String newState;
        while (true) {
            newState = orderView.promptForInput("Enter new state (or press Enter to keep unchanged): ").strip().toUpperCase();
            if ("".equals(newState)) {
                newState = existingOrder.getState();
                break;
            }
            else if (orderService.validateState(newState)) {
                break;
            }
            orderView.displayError("Invalid state. Please try again.");
        }

        String newProductType;
        while (true) {
            newProductType = orderService.toTitleCase(orderView.promptForInput("Enter new product type (or press Enter to keep unchanged): ").strip());
            if ("".equals(newProductType)) {
                newProductType = existingOrder.getProductType();
                break;
            }
            else if (orderService.validateProductType(newProductType)) {
                break;
            }
            orderView.displayError("Invalid product type. Please try again.");
        }

        String newAreaInput;
        while (true) {
            newAreaInput = orderView.promptForInput("Enter new area (min 100 sq ft) (or press Enter to keep unchanged): ").strip();
            if ("".equals(newAreaInput)) {
                newAreaInput = existingOrder.getArea().toString();
                break;
            }
            else if (orderService.validateArea(newAreaInput)) {
                break;
            }
            orderView.displayError("Area must be a positive decimal number and at least 100 sq ft. Please try again.");
        }
        
        orderService.updateOrder(orderNumber, newCustomerName, newState, newProductType, new BigDecimal(newAreaInput), orderDate);
        orderView.displaySuccess("Order updated successfully.");
    }

    private void removeOrder() throws SQLException {
        // Prompt for order number and date, confirm deletion

        LocalDate orderDate = getValidOrderDate();
        int orderNumber = getValidOrderNumber();
        
        Order existingOrder = orderService.getOrderByNumberAndDate(orderNumber, orderDate);
        if (existingOrder == null) {
            orderView.displayError("Order not found.");
            return;
        }
        
        System.out.println("Order to be deleted: " + existingOrder);
        String confirmation = orderView.promptForInput("Are you sure you want to delete this order? (Y/N): ");
        if (confirmation.equalsIgnoreCase("Y")) {
            orderService.removeOrder(orderNumber, orderDate);
            orderView.displaySuccess("Order deleted successfully.");
        } else {
            orderView.displaySuccess("Order deletion canceled.");
        }
    }

    private void exportData() {
        System.out.println("This functionality is currently unavailable.");
//        orderService.exportAllData();
//        displaySuccess("Data exported successfully.");
    }
}


