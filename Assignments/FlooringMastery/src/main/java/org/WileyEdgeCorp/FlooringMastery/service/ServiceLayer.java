package org.WileyEdgeCorp.FlooringMastery.service;

import org.WileyEdgeCorp.FlooringMastery.dto.Order;
import org.WileyEdgeCorp.FlooringMastery.dto.Product;
import org.WileyEdgeCorp.FlooringMastery.dto.Tax;

import java.time.LocalDate;
import java.util.List;

public interface ServiceLayer {

    public int getNextOrderNumber ();

    public Order addOrder(Order order);

    public Order getOrder(LocalDate date, int orderNumber);

    public Order editOrder(LocalDate date, int orderNumber);

    public Order removeOrder(LocalDate date, int orderNumber);

    public void exportData();

    public List<Tax> getTaxes();

    public List<Product> getProducts();

}
