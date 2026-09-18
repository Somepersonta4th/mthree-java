package org.WileyEdgeCorp.FlooringMastery.dao;

import org.WileyEdgeCorp.FlooringMastery.dto.Product;
import org.WileyEdgeCorp.FlooringMastery.exceptions.PersistenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductDaoFileImplTest {

    /* test plan

    get all products
    * taxDao.getAllProducts -> products

     */

    private ProductDao productDao;

    private String testFile = "testProducts.txt";
    private String testProductString = """
            ProductType::CostPerSquareFoot::LaborCostPerSquareFoot
            TestProduct::1::2""";

    @BeforeEach
    public void setUp() throws IOException, PersistenceException {
        // create dao
        productDao = new ProductDaoFileImpl(testFile);

        // create blank test file
        FileWriter writer = new FileWriter(testFile);
        writer.append(testProductString);
        writer.flush();
        writer.close();

    }

    @Test
    public void getAllProductsTest() {

        List<Product> products = productDao.getAllProducts();

        Assertions.assertEquals("TestProduct", products.get(0).getProductType());
    }

}
