/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.flooringmasteryproject.dao;

import com.mthree.flooringmasteryproject.model.Order;
import com.mthree.flooringmasteryproject.model.Product;
import com.mthree.flooringmasteryproject.model.Tax;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author khali
 */

public class MySQLLocalDatabaseConnection {
    public static void main(String[] args) {
        
        DatabaseSetup dbConnection = new DatabaseSetup();
        OrderDAO orderDao = new OrderDAO();
        TaxDAO taxDao = new TaxDAO();
        ProductDAO productDao = new ProductDAO();
        String databaseName = "flooring";
        String tableName = "ORDERS";
        try {
            dbConnection.setConnection(dbConnection.getHost(), dbConnection.getPort(), dbConnection.getUserName(), dbConnection.getPassword());
            try {
                dbConnection.createDatabase(databaseName);
                dbConnection.createTable(tableName);
                dbConnection.createTable("products");
                dbConnection.createTable("taxes");
//                Order order4 = new Order("Albert Einstein", "KY", new BigDecimal(6.00), "Carpet", new BigDecimal(217.00), new BigDecimal(2.25), new BigDecimal(2.10), new BigDecimal(488.25), new BigDecimal(455.70), new BigDecimal(56.64), new BigDecimal(1000.59), LocalDate.parse("2025-06-02"));
//                System.out.println(orderDao.createOrder(dbConnection.getConnection(), order4));
//                System.out.println(orderDao.createOrder(dbConnection.getConnection(), order4));
//                ArrayList<Order> orders = orderDao.getOrdersByDate(dbConnection.getConnection(), LocalDate.parse("2025-06-02"), new ArrayList<>());
//                for (Order order : orders) {
//                    System.out.println(order);
//                }
//                Order order5 = new Order(4, "Albieeeeeeeeee", "KY", new BigDecimal(6.00), "Carpet", new BigDecimal(217.00), new BigDecimal(2.25), new BigDecimal(2.10), new BigDecimal(488.25), new BigDecimal(455.70), new BigDecimal(56.64), new BigDecimal(1000.59), LocalDate.parse("2025-06-02"));
//                orderDao.updateOrder(dbConnection.getConnection(), order5);
//                ArrayList<Order> orders2 = orderDao.getAllOrders(dbConnection.getConnection(), new ArrayList<>());
//                for (Order order : orders2) {
//                    System.out.println(order);
//                }
//                System.out.println("doing.... " + orderDao.readOrder(dbConnection.getConnection(), 4, LocalDate.parse("2025-06-02"), new Order()));
//                orderDao.deleteOrder(dbConnection.getConnection(), 4, LocalDate.parse("2025-06-02"));
//                System.out.println("doing.... " + orderDao.readOrder(dbConnection.getConnection(), 4, LocalDate.parse("2025-06-02"), new Order()));
//                Tax tax1 = new Tax("CN", "Canada", new BigDecimal(22));
//                taxDao.createTax(dbConnection.getConnection(), tax1);
//                ArrayList<Tax> taxes1 = taxDao.getAllTaxes(dbConnection.getConnection(), new ArrayList<>());
//                for (Tax tax : taxes1) {
//                    System.out.println(tax);
//                }
//                tax1 = new Tax("CN", "Canada", new BigDecimal(10));
//                taxDao.updateTax(dbConnection.getConnection(), tax1);
//                System.out.println(taxDao.readTax(dbConnection.getConnection(), tax1.getStateAbbreviation(), new Tax()));
//                taxDao.deleteTax(dbConnection.getConnection(), tax1.getStateAbbreviation());
//                System.out.println(taxDao.readTax(dbConnection.getConnection(), tax1.getStateAbbreviation(), new Tax()));
//                taxDao.deleteTax(dbConnection.getConnection(), tax1.getStateAbbreviation());
//                System.out.println(taxDao.readTax(dbConnection.getConnection(), tax1.getStateAbbreviation(), new Tax()));
//                Product p1 = new Product("wallpanels", new BigDecimal(10.10), new BigDecimal(4));
//                productDao.createProduct(dbConnection.getConnection(), p1);
//                System.out.println("4");
//                ArrayList<Product> ps = productDao.getAllProducts(dbConnection.getConnection(), new ArrayList<>());
//                for (Product p : ps) {
//                    System.out.println(p);
//                }
//                System.out.println("4");
//                Product p2 = new Product("wallpanels", new BigDecimal(5.10), new BigDecimal(4));
//                productDao.updateProduct(dbConnection.getConnection(), p2);
//                System.out.println("9");
//                System.out.println(productDao.readProduct(dbConnection.getConnection(), p1.getProductType(), new Product()));
//                productDao.deleteProduct(dbConnection.getConnection(), p2.getProductType());
//                System.out.println(productDao.readProduct(dbConnection.getConnection(), p1.getProductType(), new Product()));
                
                
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dbConnection.getStatement().close();
                dbConnection.getConnection().close();
                System.out.println("Connection closed.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection not closed.");
            e.printStackTrace();
        } finally {
            System.out.println("Local JDBC program ended.");
        }
    }
}