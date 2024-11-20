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
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author khali
 */
public class OrderService implements OrderServiceInterface {
    
    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    private TaxDAO taxDAO;
    private Connection connection;
    
    public OrderService(OrderDAO orderDAO, ProductDAO productDAO, TaxDAO taxDAO, Connection dbCon) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
        this.taxDAO = taxDAO;
        this.connection = dbCon;
    }

    BigDecimal calulateMaterialCost(BigDecimal area, BigDecimal costPerSquareFoot) {
        return area.multiply(costPerSquareFoot);
    }

    BigDecimal calulateLaborCost(BigDecimal area, BigDecimal laborCostPerSquareFoot) {
        return area.multiply(laborCostPerSquareFoot);
    }

    BigDecimal calulateTax(BigDecimal materialCost, BigDecimal laborCost, BigDecimal taxRate) {
        return taxRate.divide(BigDecimal.valueOf(100)).multiply(materialCost.add(laborCost));
    }

    BigDecimal calulateTotal(BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax) {
        return materialCost.add(laborCost).add(tax);
    }

    @Override
    public Order addOrder(String customerName, String state, String productType, BigDecimal area, LocalDate date) throws SQLException {
        BigDecimal taxRate = getTaxRate(state);
        BigDecimal costPerSquareFoot = getCostPerSquareFoot(productType);
        BigDecimal laborCostPerSquareFoot = getLaborCostPerSquareFoot(productType);
        BigDecimal materialCost = calulateMaterialCost(area, costPerSquareFoot);
        BigDecimal laborCost = calulateLaborCost(area, laborCostPerSquareFoot);
        BigDecimal tax = calulateTax(materialCost, laborCost, taxRate);
        BigDecimal total = calulateTotal(materialCost, laborCost, tax);
        Order order = new Order(customerName, state, taxRate, productType, area, costPerSquareFoot, laborCostPerSquareFoot, materialCost, laborCost, tax, total, date);
        return orderDAO.createOrder(connection, order);
    }
    
    @Override
    public void updateOrder(int orderNumber, String customerName, String state, String productType, BigDecimal area, LocalDate date) throws SQLException {
        BigDecimal taxRate = getTaxRate(state);
        BigDecimal costPerSquareFoot = getCostPerSquareFoot(productType);
        BigDecimal laborCostPerSquareFoot = getLaborCostPerSquareFoot(productType);
        BigDecimal materialCost = calulateMaterialCost(area, costPerSquareFoot);
        BigDecimal laborCost = calulateLaborCost(area, laborCostPerSquareFoot);
        BigDecimal tax = calulateTax(materialCost, laborCost, taxRate);
        BigDecimal total = calulateTotal(materialCost, laborCost, tax);
        Order order = new Order(orderNumber, customerName, state, taxRate, productType, area, costPerSquareFoot, laborCostPerSquareFoot, materialCost, laborCost, tax, total, date);
        orderDAO.updateOrder(connection, order);
    }
    
    @Override
    public void removeOrder(int orderNumber, LocalDate date) throws SQLException {
        orderDAO.deleteOrder(connection, orderNumber, date);
    }
    
    @Override
    public Order getOrderByNumberAndDate(int orderNumber, LocalDate date) throws SQLException {
        return orderDAO.readOrder(connection, orderNumber, date, new Order());
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
        String regex = "[a-z0-9,.]+(\s[a-z0-9,.]+)*";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(customerName);
        return matcher.find();
    }

    public boolean validateState(String state) throws SQLException {
        return getAvailableStates().contains(state);
    }

    public boolean validateProductType(String product) throws SQLException {
        return getAvailableProducts().contains(product);
    }

    public boolean validateArea(BigDecimal areaInput) {
        return areaInput.compareTo(new BigDecimal(100)) < 0;
    }
}
