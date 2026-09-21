package org.WileyEdgeCorp.FlooringMastery.dao;

import org.WileyEdgeCorp.FlooringMastery.dto.Product;
import org.WileyEdgeCorp.FlooringMastery.exceptions.PersistenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductDaoFileImplTest {

    /* test plan

    get all products
    * taxDao.getAllProducts -> products

     */

    private ProductDao productDao;

    private String testFile = "src/test/java/org/WileyEdgeCorp/FlooringMastery/Data/testProducts.txt";
    private String testProductString = """
            ProductType::CostPerSquareFoot::LaborCostPerSquareFoot
            TestProduct::1.00::2.00""";

    @BeforeEach
    public void setUp() throws IOException, PersistenceException {
        // create test file
        PrintWriter writer = new PrintWriter(new FileWriter(testFile));
        writer.print(testProductString);
        writer.flush();
        writer.close();

        // create dao
        productDao = new ProductDaoFileImpl(testFile);

    }

    @Test
    public void getAllProductsTest() {

        List<Product> products = productDao.getAllProducts();

        Assertions.assertEquals("TestProduct", products.get(0).getProductType());
    }

}
