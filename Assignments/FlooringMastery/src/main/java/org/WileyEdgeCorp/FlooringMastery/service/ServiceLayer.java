package org.WileyEdgeCorp.FlooringMastery.service;

import org.WileyEdgeCorp.FlooringMastery.exceptions.DataCollisionException;
import org.WileyEdgeCorp.FlooringMastery.exceptions.NoDataLoaded;
import org.WileyEdgeCorp.FlooringMastery.dto.Order;
import org.WileyEdgeCorp.FlooringMastery.dto.Product;
import org.WileyEdgeCorp.FlooringMastery.dto.Tax;
import org.WileyEdgeCorp.FlooringMastery.exceptions.NoSuchOrderException;
import org.WileyEdgeCorp.FlooringMastery.exceptions.PersistenceException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ServiceLayer {

    //public int getNextOrderNumber ();

    public Order addOrder(Order order) throws DataCollisionException;

    public Order getOrder(int orderNumber);

    public List<Order> getOrders();

    public Order editOrder(Order newOrder) throws  NoSuchOrderException;

    public Order removeOrder(int orderNumber);

    public void exportData() throws PersistenceException;

    public List<Tax> getTaxes() throws NoDataLoaded;

    public List<Product> getProducts() throws NoDataLoaded;

    Map<Integer, Date> loadOrderNumbers() throws PersistenceException;

    void loadDate(Date dateToUse) throws PersistenceException;
}
