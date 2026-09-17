package org.WileyEdgeCorp.FlooringMastery.dao.stubs;

import org.WileyEdgeCorp.FlooringMastery.dao.ProductDao;
import org.WileyEdgeCorp.FlooringMastery.dto.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoStub implements ProductDao {
    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product("TestProduct",new BigDecimal("1"),new BigDecimal("1")));
        return products;
    }
}
