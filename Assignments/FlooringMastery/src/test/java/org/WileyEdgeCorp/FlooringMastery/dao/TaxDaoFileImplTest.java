package org.WileyEdgeCorp.FlooringMastery.dao;

import org.WileyEdgeCorp.FlooringMastery.dto.Tax;
import org.WileyEdgeCorp.FlooringMastery.exceptions.PersistenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TaxDaoFileImplTest {

    /* test plan

    get all tax
    * taxDao.getAllTaxes -> taxes

     */

    private TaxDao taxDao;

    private Tax newTax;
    private String testFile = "src/test/java/org/WileyEdgeCorp/FlooringMastery/Data/testTaxes.txt";
    private String testTaxString = """
            State::StateName::TaxRate
            TS::TestState::4.45""";

    @BeforeEach
    public void setUp() throws IOException, PersistenceException {
        // create test file
        PrintWriter writer = new PrintWriter(new FileWriter(testFile));
        writer.println(testTaxString);
        writer.flush();
        writer.close();

        // create dao
        taxDao = new TaxDaoFileImpl(testFile);

    }

    @Test
    public void getAllProductsTest() {

        List<Tax> taxes = taxDao.getAllTaxes();

        Assertions.assertEquals("TestState", taxes.get(0).getState());
    }
}
