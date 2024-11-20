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
public class Tax {
    @Getter @Setter private String stateAbbreviation;
    @Getter @Setter private String stateName;
    @Getter @Setter private BigDecimal taxRate;

    public void setState(String ny) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
