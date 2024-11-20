/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.flooringmasteryproject.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author khali
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    @Getter @Setter private String productType;
    @Getter @Setter private BigDecimal costPerSquareFoot;
    @Getter @Setter private BigDecimal laborCostPerSquareFoot;
}
