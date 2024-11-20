/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.flooringmasteryproject.dao;

import com.mthree.flooringmasteryproject.dao.interfaces.OrderDAOInterface;
import com.mthree.flooringmasteryproject.model.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author khali
 */
public class OrderDAO implements OrderDAOInterface {
    
    @Override
    public Order createOrder(Connection connection, Order order) throws SQLException {
        String sql = "INSERT INTO ORDERS "
                + "(CustomerName, State, TaxRate, ProductType, Area, CostPerSquareFoot, LaborCostPerSquareFoot, MaterialCost, LaborCost, Tax, Total, Date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement pstatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstatement.setString(1, order.getCustomerName());
            pstatement.setString(2, order.getState());
            pstatement.setDouble(3, order.getTaxRate().doubleValue());
            pstatement.setString(4, order.getProductType());
            pstatement.setDouble(5, order.getArea().doubleValue());
            pstatement.setDouble(6, order.getCostPerSquareFoot().doubleValue());
            pstatement.setDouble(7, order.getLaborCostPerSquareFoot().doubleValue());
            pstatement.setDouble(8, order.getMaterialCost().doubleValue());
            pstatement.setDouble(9, order.getLaborCost().doubleValue());
            pstatement.setDouble(10, order.getTax().doubleValue());
            pstatement.setDouble(11, order.getTotal().doubleValue());
            pstatement.setDate(12, Date.valueOf(order.getDate()));
            pstatement.executeUpdate();
            
            ResultSet generatedKeys = pstatement.getGeneratedKeys();
            while(generatedKeys.next()) {
                order.setOrderNumber(generatedKeys.getInt(1));
            }
        }   
        return order;
    }

    @Override
    public Order readOrder(Connection connection, int orderNumber, LocalDate date, Order order) throws SQLException {
        
        String sql = "SELECT * FROM ORDERS WHERE OrderNumber = ? AND Date = ?;";
        try (PreparedStatement pstatement = connection.prepareStatement(sql)) {
            pstatement.setInt(1, orderNumber);
            pstatement.setDate(2, Date.valueOf(date));
            
            ResultSet rs = pstatement.executeQuery();
            while(rs.next()) {
                order.setOrderNumber(rs.getInt(1));
                order.setCustomerName(rs.getString(2));
                order.setState(rs.getString(3));
                order.setTaxRate(BigDecimal.valueOf(rs.getDouble(4)));
                order.setProductType(rs.getString(5));
                order.setArea(BigDecimal.valueOf(rs.getDouble(6)));
                order.setCostPerSquareFoot(BigDecimal.valueOf(rs.getDouble(7)));
                order.setLaborCostPerSquareFoot(BigDecimal.valueOf(rs.getDouble(8)));
                order.setMaterialCost(BigDecimal.valueOf(rs.getDouble(9)));
                order.setLaborCost(BigDecimal.valueOf(rs.getDouble(10)));
                order.setTax(BigDecimal.valueOf(rs.getDouble(11)));
                order.setTotal(BigDecimal.valueOf(rs.getDouble(12)));
                order.setDate(rs.getDate(13).toLocalDate());
            }
        }
        return order;
    }

    @Override
    public void updateOrder(Connection connection, Order order) throws SQLException {
        String sql = "UPDATE ORDERS SET "
                + "CustomerName = ?, "
                + "State = ?, "
                + "TaxRate = ?, "
                + "ProductType = ?, "
                + "Area = ?, "
                + "CostPerSquareFoot = ?, "
                + "LaborCostPerSquareFoot = ?, "
                + "MaterialCost = ?, "
                + "LaborCost = ?, "
                + "Tax = ?, "
                + "Total = ? "
                + "WHERE OrderNumber = ? AND Date = ?;";
        try (PreparedStatement pstatement = connection.prepareStatement(sql)) {
            pstatement.setString(1, order.getCustomerName());
            pstatement.setString(2, order.getState());
            pstatement.setDouble(3, order.getTaxRate().doubleValue());
            pstatement.setString(4, order.getProductType());
            pstatement.setDouble(5, order.getArea().doubleValue());
            pstatement.setDouble(6, order.getCostPerSquareFoot().doubleValue());
            pstatement.setDouble(7, order.getLaborCostPerSquareFoot().doubleValue());
            pstatement.setDouble(8, order.getMaterialCost().doubleValue());
            pstatement.setDouble(9, order.getLaborCost().doubleValue());
            pstatement.setDouble(10, order.getTax().doubleValue());
            pstatement.setDouble(11, order.getTotal().doubleValue());
            pstatement.setInt(12, order.getOrderNumber());
            pstatement.setDate(13, Date.valueOf(order.getDate()));
            
            pstatement.executeUpdate();
        }
    }

    @Override
    public void deleteOrder(Connection connection, int orderNumber, LocalDate date) throws SQLException {
        
        String sql = "DELETE FROM ORDERS WHERE OrderNumber = ? AND Date = ?;";
        try (PreparedStatement pstatement = connection.prepareStatement(sql)) {
            pstatement.setInt(1, orderNumber);
            pstatement.setDate(2, Date.valueOf(date));
            
            pstatement.executeUpdate();
        }
    }

    @Override
    public ArrayList<Order> getOrdersByDate(Connection connection, LocalDate date, ArrayList<Order> orders) throws SQLException {
        Order order;
        String sql = "SELECT * FROM ORDERS WHERE Date = ?;";
        try (PreparedStatement pstatement = connection.prepareStatement(sql)) {
            pstatement.setDate(1, Date.valueOf(date));
            
            ResultSet rs = pstatement.executeQuery();
            while(rs.next()) {
                order = new Order();
                order.setOrderNumber(rs.getInt(1));
                order.setCustomerName(rs.getString(2));
                order.setState(rs.getString(3));
                order.setTaxRate(BigDecimal.valueOf(rs.getDouble(4)));
                order.setProductType(rs.getString(5));
                order.setArea(BigDecimal.valueOf(rs.getDouble(6)));
                order.setCostPerSquareFoot(BigDecimal.valueOf(rs.getDouble(7)));
                order.setLaborCostPerSquareFoot(BigDecimal.valueOf(rs.getDouble(8)));
                order.setMaterialCost(BigDecimal.valueOf(rs.getDouble(9)));
                order.setLaborCost(BigDecimal.valueOf(rs.getDouble(10)));
                order.setTax(BigDecimal.valueOf(rs.getDouble(11)));
                order.setTotal(BigDecimal.valueOf(rs.getDouble(12)));
                order.setDate(rs.getDate(13).toLocalDate());
                orders.add(order);
            }
        }
        return orders;
    }

    @Override
    public ArrayList<Order> getAllOrders(Connection connection, ArrayList<Order> orders) throws SQLException {
        Order order;
        String sql = "SELECT * FROM ORDERS;";
        try (PreparedStatement pstatement = connection.prepareStatement(sql)) {
            ResultSet rs = pstatement.executeQuery();
            while(rs.next()) {
                order = new Order();
                order.setOrderNumber(rs.getInt(1));
                order.setCustomerName(rs.getString(2));
                order.setState(rs.getString(3));
                order.setTaxRate(BigDecimal.valueOf(rs.getDouble(4)));
                order.setProductType(rs.getString(5));
                order.setArea(BigDecimal.valueOf(rs.getDouble(6)));
                order.setCostPerSquareFoot(BigDecimal.valueOf(rs.getDouble(7)));
                order.setLaborCostPerSquareFoot(BigDecimal.valueOf(rs.getDouble(8)));
                order.setMaterialCost(BigDecimal.valueOf(rs.getDouble(9)));
                order.setLaborCost(BigDecimal.valueOf(rs.getDouble(10)));
                order.setTax(BigDecimal.valueOf(rs.getDouble(11)));
                order.setTotal(BigDecimal.valueOf(rs.getDouble(12)));
                order.setDate(rs.getDate(13).toLocalDate());
                orders.add(order);
            }
        }
        return orders;
    }
}
