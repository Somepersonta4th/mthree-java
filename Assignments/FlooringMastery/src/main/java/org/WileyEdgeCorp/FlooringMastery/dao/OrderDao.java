package org.WileyEdgeCorp.FlooringMastery.dao;

import org.WileyEdgeCorp.FlooringMastery.dto.Order;
import org.WileyEdgeCorp.FlooringMastery.exceptions.PersistenceException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderDao {

    //public int getNextOrderNumber();

    public Order addOrder(Order order);

    public Order getOrder(int orderNumber);

    public Order editOrder(Order newOrder);

    public List<Order> getOrders();

    public Order removeOrder(int orderNumber);

    void loadDate(Date date) throws PersistenceException;

    Map<Integer, Date> loadOrderNumbers() throws PersistenceException;

    void exportData() throws PersistenceException;
}
