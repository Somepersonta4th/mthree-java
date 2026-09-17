package org.WileyEdgeCorp.FlooringMastery.service;

import org.WileyEdgeCorp.FlooringMastery.dto.Order;
import org.WileyEdgeCorp.FlooringMastery.dto.Product;
import org.WileyEdgeCorp.FlooringMastery.dto.Tax;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ServiceLayer {

    //public int getNextOrderNumber ();

    public Order addOrder(Order order);

    public Order getOrder(Date date, int orderNumber);

    public Order editOrder(Date date, int orderNumber, Order newOrder);

    public Order removeOrder(Date date, int orderNumber);

    public void exportData();

    public List<Tax> getTaxes();

    public List<Product> getProducts();

}
