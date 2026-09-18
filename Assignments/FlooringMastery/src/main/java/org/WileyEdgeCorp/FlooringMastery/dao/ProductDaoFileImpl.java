package org.WileyEdgeCorp.FlooringMastery.dao;

import org.WileyEdgeCorp.FlooringMastery.dto.Product;
import org.WileyEdgeCorp.FlooringMastery.exceptions.PersistenceException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

public class ProductDaoFileImpl implements ProductDao {

    private String filePath = "src/main/java/org/WileyEdgeCorp/FlooringMastery/Data/Products.txt";
    private Map<String,Product> allProducts = new HashMap<>();
    private final String DELIMITER = "::";

    public ProductDaoFileImpl() throws PersistenceException {
        loadProducts();
    }

    public ProductDaoFileImpl(String filePath) throws PersistenceException {
        this.filePath = filePath;
        loadProducts();
    }

    @Override
    public List<Product> getAllProducts() {
        return allProducts.values().stream().toList();
    }

    private void loadProducts() throws PersistenceException {

        Scanner scanner;

        try {
            // Create Scanner
            scanner = new Scanner(new BufferedReader(new FileReader(filePath)));
        } catch (FileNotFoundException e) {
            throw new PersistenceException("could not load product file", e);
        }

        //remove first line
        try {
            String propertyNames = scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new PersistenceException("malformed product data",e);
        }

        String currentLine;
        Product currentProduct;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentProduct = unmarshallProduct(currentLine);
            allProducts.put(currentProduct.getProductType(),currentProduct);
        }

    }

    private Product unmarshallProduct(String raw) throws PersistenceException {
        String[] properties = raw.split(DELIMITER);

        try {
            //map properties
            //ProductType::CostPerSquareFoot::LaborCostPerSquareFoot
            return new Product(properties[0], new BigDecimal(properties[1]), new BigDecimal(properties[2]));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new PersistenceException("malformed product data", e);
        }


    }
}
