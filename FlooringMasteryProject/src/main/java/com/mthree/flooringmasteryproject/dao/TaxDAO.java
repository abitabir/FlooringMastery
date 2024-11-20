/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.flooringmasteryproject.dao;

import com.mthree.flooringmasteryproject.dao.interfaces.TaxDAOInterface;
import com.mthree.flooringmasteryproject.model.Tax;
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
public class TaxDAO implements TaxDAOInterface {
        
    @Override
    public void createTax(Connection connection, Tax tax) throws SQLException {
        String sql = "INSERT INTO TAXES "
                + "(StateAbbreviation, StateName, TaxRate) "
                + "VALUES (?, ?, ?);";
        try (PreparedStatement pstatement = connection.prepareStatement(sql)) {
            pstatement.setString(1, tax.getStateAbbreviation());
            pstatement.setString(2, tax.getStateName());
            pstatement.setDouble(3, tax.getTaxRate().doubleValue());
            pstatement.executeUpdate();
            
        }
    }

    @Override
    public Tax readTax(Connection connection, String stateAbbreviation, Tax tax) throws SQLException {
        
        String sql = "SELECT * FROM TAXES WHERE StateAbbreviation = ?;";
        try (PreparedStatement pstatement = connection.prepareStatement(sql)) {
            pstatement.setString(1, stateAbbreviation);
            
            ResultSet rs = pstatement.executeQuery();
            while(rs.next()) {
                tax.setStateAbbreviation(rs.getString(1));
                tax.setStateName(rs.getString(2));
                tax.setTaxRate(new BigDecimal(Double.toString(rs.getDouble(3))));
            }
        }
        return tax;
    }

    @Override
    public void updateTax(Connection connection, Tax tax) throws SQLException {
        
        String sql = "UPDATE TAXES SET "
                + "StateName = ?, "
                + "TaxRate = ? "
                + "WHERE StateAbbreviation = ?;";
        try (PreparedStatement pstatement = connection.prepareStatement(sql)) {
            pstatement.setString(1, tax.getStateName());
            pstatement.setDouble(2, tax.getTaxRate().doubleValue());
            pstatement.setString(3, tax.getStateAbbreviation());
            
            pstatement.executeUpdate();
        }
    }

    @Override
    public void deleteTax(Connection connection, String stateAbbreviation) throws SQLException {
        
        String sql = "DELETE FROM TAXES WHERE StateAbbreviation = ?;";
        try (PreparedStatement pstatement = connection.prepareStatement(sql)) {
            pstatement.setString(1, stateAbbreviation);
            
            pstatement.executeUpdate();
        }
    }

    @Override
    public ArrayList<Tax> getAllTaxes(Connection connection, ArrayList<Tax> taxes) throws SQLException {
        
        Tax tax;
        String sql = "SELECT * FROM TAXES;";
        try (PreparedStatement pstatement = connection.prepareStatement(sql)) {
            ResultSet rs = pstatement.executeQuery();
            while(rs.next()) {
                tax = new Tax();
                tax.setStateAbbreviation(rs.getString(1));
                tax.setStateName(rs.getString(2));
                tax.setTaxRate(new BigDecimal(Double.toString(rs.getDouble(3))));
                taxes.add(tax);
            }
        }
        return taxes;
    }
}
