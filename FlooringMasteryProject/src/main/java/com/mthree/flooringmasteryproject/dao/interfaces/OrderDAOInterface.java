/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mthree.flooringmasteryproject.dao.interfaces;

import com.mthree.flooringmasteryproject.model.Order;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author khali
 */
public interface OrderDAOInterface {
    Order createOrder(Connection connection, Order order) throws SQLException;
    Order readOrder(Connection connection, int orderNumber, LocalDate date, Order order) throws SQLException;
    void updateOrder(Connection connection, Order order) throws SQLException;
    void deleteOrder(Connection connection, int orderNumber, LocalDate date) throws SQLException;
    ArrayList<Order> getOrdersByDate(Connection connection, LocalDate date, ArrayList<Order> orders) throws SQLException;
    ArrayList<Order> getAllOrders(Connection connection, ArrayList<Order> orders) throws SQLException;
}
