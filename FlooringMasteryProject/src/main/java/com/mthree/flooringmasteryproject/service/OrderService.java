/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.flooringmasteryproject.service;

import com.mthree.flooringmasteryproject.dao.OrderDAO;
import com.mthree.flooringmasteryproject.dao.ProductDAO;
import com.mthree.flooringmasteryproject.dao.TaxDAO;
import com.mthree.flooringmasteryproject.model.Order;
import com.mthree.flooringmasteryproject.model.Product;
import com.mthree.flooringmasteryproject.model.Tax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author khali
 */
@Service
public class OrderService implements OrderServiceInterface {
    
    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    private TaxDAO taxDAO;
    private Connection connection;
    
    @Autowired
    public OrderService(OrderDAO orderDAO, ProductDAO productDAO, TaxDAO taxDAO, Connection dbCon) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
        this.taxDAO = taxDAO;
        this.connection = dbCon;
    }

    public BigDecimal calculateMaterialCost(BigDecimal area, BigDecimal costPerSquareFoot) {
        if (area == null || costPerSquareFoot == null || area.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid area or cost per square foot");
        }
        return area.multiply(costPerSquareFoot).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateLaborCost(BigDecimal area, BigDecimal laborCostPerSquareFoot) {
        if (area == null || laborCostPerSquareFoot == null || area.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid area or labor cost per square foot");
        }
        return area.multiply(laborCostPerSquareFoot).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateTax(BigDecimal materialCost, BigDecimal laborCost, BigDecimal taxRate) {
        if (materialCost == null || laborCost == null || taxRate == null || taxRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Invalid cost or tax rate");
        }
        return materialCost.add(laborCost).multiply(taxRate.divide(new BigDecimal(100), 4, RoundingMode.HALF_UP)).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateTotal(BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax) {
        if (materialCost == null || laborCost == null || tax == null) {
            throw new IllegalArgumentException("Invalid cost values");
        }
        return materialCost.add(laborCost).add(tax).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public Order addOrder(String customerName, String state, String productType, BigDecimal area, LocalDate date) throws SQLException {
        BigDecimal taxRate = getTaxRate(state);
        BigDecimal costPerSquareFoot = getCostPerSquareFoot(productType);
        BigDecimal laborCostPerSquareFoot = getLaborCostPerSquareFoot(productType);
        BigDecimal materialCost = calculateMaterialCost(area, costPerSquareFoot);
        BigDecimal laborCost = calculateLaborCost(area, laborCostPerSquareFoot);
        BigDecimal tax = calculateTax(materialCost, laborCost, taxRate);
        BigDecimal total = calculateTotal(materialCost, laborCost, tax);
        Order order = new Order(customerName, state, taxRate, productType, area, costPerSquareFoot, laborCostPerSquareFoot, materialCost, laborCost, tax, total, date);
        return orderDAO.createOrder(connection, order);
    }
    
    @Override
    public void updateOrder(int orderNumber, String customerName, String state, String productType, BigDecimal area, LocalDate date) throws SQLException {
        BigDecimal taxRate = getTaxRate(state);
        BigDecimal costPerSquareFoot = getCostPerSquareFoot(productType);
        BigDecimal laborCostPerSquareFoot = getLaborCostPerSquareFoot(productType);
        BigDecimal materialCost = calculateMaterialCost(area, costPerSquareFoot);
        BigDecimal laborCost = calculateLaborCost(area, laborCostPerSquareFoot);
        BigDecimal tax = calculateTax(materialCost, laborCost, taxRate);
        BigDecimal total = calculateTotal(materialCost, laborCost, tax);
        Order order = new Order(orderNumber, customerName, state, taxRate, productType, area, costPerSquareFoot, laborCostPerSquareFoot, materialCost, laborCost, tax, total, date);
        orderDAO.updateOrder(connection, order);
    }
    
    @Override
    public void removeOrder(int orderNumber, LocalDate date) throws SQLException {
        orderDAO.deleteOrder(connection, orderNumber, date);
    }
    
    @Override
    public Order getOrderByNumberAndDate(int orderNumber, LocalDate date) throws SQLException {
        Order order = orderDAO.readOrder(connection, orderNumber, date, new Order());
        return order.getOrderNumber() != null ? order : null;
    }
    
    @Override
    public ArrayList<Order> getOrdersByDate(LocalDate date) throws SQLException {
        return orderDAO.getOrdersByDate(connection, date, new ArrayList<>());
    }
    
    @Override
    public ArrayList<String> getAvailableProducts() throws SQLException {
        ArrayList<String> productTypes = new ArrayList<>();
        ArrayList<Product> products = productDAO.getAllProducts(connection, new ArrayList<>());
        for (Product product : products) {
            productTypes.add(product.getProductType());
        }
        return productTypes;
    }

    @Override
    public ArrayList<String> getAvailableStates() throws SQLException {
        ArrayList<String> availableStates = new ArrayList<>();
        ArrayList<Tax> taxes = taxDAO.getAllTaxes(connection, new ArrayList<>());
        for (Tax tax : taxes) {
            availableStates.add(tax.getStateAbbreviation());
        }
        return availableStates;
    }
    
    @Override
    public BigDecimal getTaxRate(String stateAbbreviation) throws SQLException {
        return taxDAO.readTax(connection, stateAbbreviation, new Tax()).getTaxRate();
    }
    
    @Override
    public BigDecimal getCostPerSquareFoot(String productType) throws SQLException {
        return productDAO.readProduct(connection, productType, new Product()).getCostPerSquareFoot();
    }
    
    @Override
    public BigDecimal getLaborCostPerSquareFoot(String productType) throws SQLException {
        return productDAO.readProduct(connection, productType, new Product()).getLaborCostPerSquareFoot();   
    }
    
    public boolean validateCustomerName(String customerName) {
        String regex = "^[a-zA-Z,.]+(\\s[a-zA-Z,.]+)*$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(customerName);
        return matcher.find();
    }

    public boolean validateState(String state) throws SQLException {
        String regex = "^[A-Z]{2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(state);
        return matcher.find() && getAvailableStates().contains(state);
    }

    public boolean validateProductType(String product) throws SQLException {
        String regex = "^[A-Z][a-z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(product);
        return matcher.find() && getAvailableProducts().contains(product);
    }

    public boolean validateArea(String areaInput) {
        String regex = "^[0-9]+(?:\\.[0-9]*)?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(areaInput);
        return matcher.find() && new BigDecimal(areaInput).compareTo(new BigDecimal(100)) < 0;
    }

    public boolean validateOrderNumber(String orderNumber) {
        String regex = "^\\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(orderNumber);
        return matcher.find();
    }
    
    public boolean validateOrderDate(String orderDate) {
        String regex = "^(?:19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(orderDate);
        return matcher.find();
    }
    
    public String toTitleCase(String str) {
        String regex = "\\b\\w";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        StringBuffer result = new StringBuffer();
        
        while (matcher.find()) {
            matcher.appendReplacement(result, matcher.group().toUpperCase());
        }
        
        matcher.appendTail(result);
        return result.toString();
    }
}
