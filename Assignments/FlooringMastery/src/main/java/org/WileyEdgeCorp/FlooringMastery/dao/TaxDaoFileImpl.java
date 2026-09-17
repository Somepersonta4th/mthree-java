package org.WileyEdgeCorp.FlooringMastery.dao;

import org.WileyEdgeCorp.FlooringMastery.dto.Tax;

import java.util.List;

public class TaxDaoFileImpl implements TaxDao {

    private String filePath;

    public TaxDaoFileImpl() {}

    public TaxDaoFileImpl(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Tax> getAllTaxes() {
        return List.of();
    }
}
