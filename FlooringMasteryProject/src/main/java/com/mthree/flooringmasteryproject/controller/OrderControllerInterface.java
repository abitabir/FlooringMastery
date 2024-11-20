/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mthree.flooringmasteryproject.controller;

import com.mthree.flooringmasteryproject.model.Order;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 *
 * @author khali
 */
public interface OrderControllerInterface {
    ArrayList<Order> getOrdersByDate(LocalDate date) throws SQLException;
    Order addOrder(String customerName, String state, String productType, BigDecimal area, LocalDate date) throws SQLException;
    void editOrder(int orderNumber, String customerName, String state, String productType, BigDecimal area, LocalDate date) throws SQLException;
    void removeOrder(int orderNumber, LocalDate date) throws SQLException;
//    void exportData();

}
