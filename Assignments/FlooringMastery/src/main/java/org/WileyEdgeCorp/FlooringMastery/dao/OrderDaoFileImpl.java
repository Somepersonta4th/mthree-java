package org.WileyEdgeCorp.FlooringMastery.dao;

import org.WileyEdgeCorp.FlooringMastery.dto.Order;

import java.util.Date;
import java.util.List;

public class OrderDaoFileImpl implements OrderDao {

    private String filePath;

    public OrderDaoFileImpl() {}

    public OrderDaoFileImpl(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Order addOrder(Order order) {
        return null;
    }

    @Override
    public Order getOrder(Date orderDate, int orderNumber) {
        return null;
    }

    @Override
    public Order editOrder(Date orderDate, int orderNumber, Order newOrder) {
        return null;
    }

    @Override
    public List<Order> getOrders() {
        return List.of();
    }

    @Override
    public Order removeOrder(Date orderDate, int orderNumber) {
        return null;
    }
}
