/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mthree.flooringmasteryproject.dao.interfaces;

import com.mthree.flooringmasteryproject.model.Tax;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author khali
 */
public interface TaxDAOInterface {
    void createTax(Connection connection, Tax tax) throws SQLException;
    Tax readTax(Connection connection, String stateAbbreviation, Tax tax) throws SQLException;
    void updateTax(Connection connection, Tax tax) throws SQLException;
    void deleteTax(Connection connection, String stateAbbreviation) throws SQLException;
    ArrayList<Tax> getAllTaxes(Connection connection, ArrayList<Tax> taxes) throws SQLException;

}
