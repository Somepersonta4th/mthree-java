package org.WileyEdgeCorp.FlooringMastery.dao;

import org.WileyEdgeCorp.FlooringMastery.dto.Tax;
import org.WileyEdgeCorp.FlooringMastery.exceptions.PersistenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TaxDaoFileImplTest {

    /* test plan

    get all tax
    * taxDao.getAllTaxes -> taxes

     */

    private TaxDao taxDao;

    private Tax newTax;
    private String testFile = "testProducts.txt";
    private String testTaxString = """
            State::StateName::TaxRate
            TS::TestState::4.45""";

    @BeforeEach
    public void setUp() throws IOException, PersistenceException {
        // create dao
        taxDao = new TaxDaoFileImpl(testFile);

        // create blank test file
        FileWriter writer = new FileWriter(testFile);
        writer.append(testTaxString);
        writer.flush();
        writer.close();
    }

    @Test
    public void getAllProductsTest() {

        List<Tax> taxes = taxDao.getAllTaxes();

        Assertions.assertEquals("TestState", taxes.get(0).getState());
    }
}
