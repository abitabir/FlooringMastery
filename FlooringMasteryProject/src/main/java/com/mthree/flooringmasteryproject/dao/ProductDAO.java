/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.flooringmasteryproject.dao;

import com.mthree.flooringmasteryproject.dao.interfaces.ProductDAOInterface;
import com.mthree.flooringmasteryproject.model.Product;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author khali
 */
public class ProductDAO implements ProductDAOInterface {

    @Override
    public void createProduct(Connection connection, Product product) throws SQLException {
        String sql = "INSERT INTO PRODUCTS "
                + "(ProductType, CostPerSquareFoot, LaborCostPerSquareFoot) "
                + "VALUES (?, ?, ?);";
        try (PreparedStatement pstatement = connection.prepareStatement(sql)) {
            pstatement.setString(1, product.getProductType());
            pstatement.setDouble(2, product.getCostPerSquareFoot().doubleValue());
            pstatement.setDouble(3, product.getLaborCostPerSquareFoot().doubleValue());
            pstatement.executeUpdate();
        }
    }
    
    @Override
    public Product readProduct(Connection connection, String productType, Product product) throws SQLException {
        String sql = "SELECT * FROM PRODUCTS WHERE ProductType = ?;";
        try (PreparedStatement pstatement = connection.prepareStatement(sql)) {
            pstatement.setString(1, productType);
            
            ResultSet rs = pstatement.executeQuery();
            while(rs.next()) {
                product.setProductType(rs.getString(1));
                product.setCostPerSquareFoot(new BigDecimal(Double.toString(rs.getDouble(2))));
                product.setLaborCostPerSquareFoot(new BigDecimal(Double.toString(rs.getDouble(3))));
            }
        }
        return product;
    }
    
    @Override
    public void updateProduct(Connection connection, Product product) throws SQLException {
        
        String sql = "UPDATE PRODUCTS SET "
                + "CostPerSquareFoot = ?, "
                + "LaborCostPerSquareFoot = ? "
                + "WHERE ProductType = ?;";
        try (PreparedStatement pstatement = connection.prepareStatement(sql)) {
            pstatement.setDouble(1, product.getCostPerSquareFoot().doubleValue());
            pstatement.setDouble(2, product.getLaborCostPerSquareFoot().doubleValue());
            pstatement.setString(3, product.getProductType());
            
            pstatement.executeUpdate();
        }
    }
    
    @Override
    public void deleteProduct(Connection connection, String productType) throws SQLException {
        
        String sql = "DELETE FROM PRODUCTS WHERE ProductType = ?;";
        try (PreparedStatement pstatement = connection.prepareStatement(sql)) {
            pstatement.setString(1, productType);
            
            pstatement.executeUpdate();
        }
    }
    
    @Override
    public ArrayList<Product> getAllProducts(Connection connection, ArrayList<Product> products) throws SQLException {
        
        Product product;
        String sql = "SELECT * FROM PRODUCTS;";
        try (PreparedStatement pstatement = connection.prepareStatement(sql)) {
            ResultSet rs = pstatement.executeQuery();
            while(rs.next()) {
                product = new Product();
                product.setProductType(rs.getString(1));
                product.setCostPerSquareFoot(new BigDecimal(Double.toString(rs.getDouble(2))));
                product.setLaborCostPerSquareFoot(new BigDecimal(Double.toString(rs.getDouble(3))));
                products.add(product);
            }
        }
        return products;
    }
}
