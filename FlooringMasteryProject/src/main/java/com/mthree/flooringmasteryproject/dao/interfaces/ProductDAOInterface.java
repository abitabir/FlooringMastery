/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mthree.flooringmasteryproject.dao.interfaces;

import com.mthree.flooringmasteryproject.model.Product;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author khali
 */
public interface ProductDAOInterface {
    void createProduct(Connection connection, Product product) throws SQLException;
    Product readProduct(Connection connection, String productType, Product product) throws SQLException;
    void updateProduct(Connection connection, Product product) throws SQLException;
    void deleteProduct(Connection connection, String productType) throws SQLException;
    ArrayList<Product> getAllProducts(Connection connection, ArrayList<Product> products) throws SQLException;
    
}
