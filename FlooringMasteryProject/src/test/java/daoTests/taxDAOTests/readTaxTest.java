/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package daoTests.taxDAOTests;

import com.mthree.flooringmasteryproject.dao.TaxDAO;
import com.mthree.flooringmasteryproject.model.Tax;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;
import daoTests.DAOTestSuiteTest;

/**
 *
 * @author khali
 */
public class readTaxTest {

    @Test
    public void readTaxTest() throws SQLException {
    
        TaxDAO taxDAO = new TaxDAO();
        Connection connection = DAOTestSuiteTest.getConnection();
        
        Tax tax = new Tax();
        tax.setStateAbbreviation("JV");
        tax.setStateName("Java");
        tax.setTaxRate(new BigDecimal("0.08"));

        taxDAO.createTax(connection, tax);

        Tax retrievedTax = taxDAO.readTax(connection, tax.getStateAbbreviation(), new Tax());

        // Assertions to verify the tax was created and retrieved correctly
        assertNotNull(retrievedTax);
        assertEquals(tax.getStateAbbreviation(), retrievedTax.getStateAbbreviation());
        assertEquals(tax.getStateName(), retrievedTax.getStateName());
        assertEquals(0, tax.getTaxRate().compareTo(retrievedTax.getTaxRate()));
    }    
}
