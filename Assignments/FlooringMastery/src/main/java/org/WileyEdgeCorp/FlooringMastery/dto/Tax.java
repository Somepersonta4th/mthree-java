package org.WileyEdgeCorp.FlooringMastery.dto;

import java.math.BigDecimal;

public class Tax {
    private String state;
    private String stateAbbreviation;
    private BigDecimal taxRate;

    public Tax (){};

    //set properties on instantiation
    public Tax(String state, String stateAbbreviation, BigDecimal taxRate) {
        this.state = state;
        this.stateAbbreviation = stateAbbreviation;
        this.taxRate = taxRate;
    }


    public String getState() {
        return state;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }
}
