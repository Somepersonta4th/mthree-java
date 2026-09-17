package org.WileyEdgeCorp.FlooringMastery.dao;

import org.WileyEdgeCorp.FlooringMastery.dto.Product;

import java.util.List;

public class ProductDaoFileImpl implements ProductDao {

    private String filePath;

    public ProductDaoFileImpl() {}

    public ProductDaoFileImpl(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}
