/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.flooringmasteryproject.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author khali
 */

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    @Getter @Setter private Integer orderNumber;
    @Getter @Setter @NonNull private String customerName;
    @Getter @Setter @NonNull private String state;
    @Getter @Setter @NonNull private BigDecimal taxRate;
    @Getter @Setter @NonNull private String productType;
    @Getter @Setter @NonNull private BigDecimal area;
    @Getter @Setter @NonNull private BigDecimal costPerSquareFoot;
    @Getter @Setter @NonNull private BigDecimal laborCostPerSquareFoot;
    @Getter @Setter @NonNull private BigDecimal materialCost;
    @Getter @Setter @NonNull private BigDecimal laborCost;
    @Getter @Setter @NonNull private BigDecimal tax;
    @Getter @Setter @NonNull private BigDecimal total;
    @Getter @Setter @NonNull private LocalDate date;
    
}
