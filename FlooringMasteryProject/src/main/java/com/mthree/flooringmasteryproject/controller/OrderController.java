/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.flooringmasteryproject.controller;

import com.mthree.flooringmasteryproject.model.Order;
import com.mthree.flooringmasteryproject.service.OrderService;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author khali
 */
public class OrderController implements OrderControllerInterface {
    
    private final OrderService orderService;
    
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @Override
    public ArrayList<Order> getOrdersByDate(LocalDate date) throws SQLException {
        return orderService.getOrdersByDate(date);
    }
    
    @Override
    public Order addOrder(String customerName, String state, String productType, BigDecimal area, LocalDate date) throws SQLException {
        return orderService.addOrder(customerName, state, productType, area, date);
    }
    
    @Override
    public void editOrder(int orderNumber, String customerName, String state, String productType, BigDecimal area, LocalDate date) throws SQLException {
        orderService.updateOrder(orderNumber, customerName, state, productType, area, date);
    }
    
    @Override
    public void removeOrder(int orderNumber, LocalDate date) throws SQLException {
        orderService.removeOrder(orderNumber, date);
    }
}



