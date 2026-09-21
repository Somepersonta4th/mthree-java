package org.WileyEdgeCorp.FlooringMastery.dao;

import org.WileyEdgeCorp.FlooringMastery.dto.Tax;
import org.WileyEdgeCorp.FlooringMastery.exceptions.PersistenceException;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class TaxDaoFileImpl implements TaxDao {

    private String filePath = "src/main/java/org/WileyEdgeCorp/FlooringMastery/Data/States.txt";
    private Map<String, Tax> allTaxes = new HashMap<>();
    private final String DELIMITER = "::";

    public TaxDaoFileImpl() throws PersistenceException {
        loadTaxes();
    }

    public TaxDaoFileImpl(String filePath) throws PersistenceException {
        this.filePath = filePath;
        loadTaxes();
    }

    @Override
    public List<Tax> getAllTaxes() {
        return allTaxes.values().stream().toList();
    }

    private void loadTaxes() throws PersistenceException {

        Scanner scanner;

        try {
            // Create Scanner
            scanner = new Scanner(new BufferedReader(new FileReader(filePath)));
        } catch (FileNotFoundException e) {
            throw new PersistenceException("could not load taxes file", e);
        }

        //remove first line
        try {
            String propertyNames = scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new PersistenceException("malformed tax data",e);
        }

        String currentLine;
        Tax currentTax;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTax = unmarshallTax(currentLine);
            allTaxes.put(currentTax.getState(),currentTax);
        }

    }

    private Tax unmarshallTax(String raw) throws PersistenceException {
        String[] properties = raw.split(DELIMITER);

        try {
            //map properties
            // state :: state abbrev :: tax rate
            return new Tax(properties[1], properties[0], new BigDecimal(properties[2]));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new PersistenceException("malformed tax data", e);
        }

    }
}
