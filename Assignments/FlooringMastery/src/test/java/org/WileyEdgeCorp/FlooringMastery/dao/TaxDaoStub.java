package org.WileyEdgeCorp.FlooringMastery.dao;

import org.WileyEdgeCorp.FlooringMastery.dto.Tax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TaxDaoStub implements TaxDao {
    @Override
    public List<Tax> getAllTaxes() {
        List<Tax> taxes = new ArrayList<Tax>();
        taxes.add(new Tax("TestState","TS",new BigDecimal("1")));
        return taxes;
    }
}
