package org.WileyEdgeCorp.FlooringMastery.dao;

import org.WileyEdgeCorp.FlooringMastery.dto.Order;
import org.WileyEdgeCorp.FlooringMastery.exceptions.PersistenceException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderDao {

    //public int getNextOrderNumber();

    public Order addOrder(Order order);

    public Order getOrder(Date orderDate, int orderNumber);

    public Order editOrder(Date orderDate, int orderNumber, Order newOrder);

    public List<Order> getOrders();

    public Order removeOrder(Date orderDate, int orderNumber);

    void loadDate(Date date) throws PersistenceException;

    Map<Integer, Date> loadOrderNumbers() throws PersistenceException;
}
