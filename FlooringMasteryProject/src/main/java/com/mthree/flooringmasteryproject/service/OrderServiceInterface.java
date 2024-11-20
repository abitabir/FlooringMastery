/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mthree.flooringmasteryproject.service;

/**
 *
 * @author khali
 */

import com.mthree.flooringmasteryproject.model.Order;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface OrderServiceInterface {
    Order addOrder(String customerName, String state, String productType, BigDecimal area, LocalDate date) throws SQLException ;
    void updateOrder(int orderNumber, String customerName, String state, String productType, BigDecimal area, LocalDate date) throws SQLException;
    void removeOrder(int orderNumber, LocalDate date) throws SQLException;
    Order getOrderByNumberAndDate(int orderNumber, LocalDate date) throws SQLException;
    ArrayList<Order> getOrdersByDate(LocalDate date) throws SQLException;
    ArrayList<String> getAvailableProducts() throws SQLException;
    ArrayList<String> getAvailableStates() throws SQLException;
    BigDecimal getTaxRate(String stateAbbreviation) throws SQLException;
    BigDecimal getCostPerSquareFoot(String productType) throws SQLException;
    BigDecimal getLaborCostPerSquareFoot(String productType) throws SQLException;
}