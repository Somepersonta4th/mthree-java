package org.WileyEdgeCorp.FlooringMastery.dao;

import org.WileyEdgeCorp.FlooringMastery.dto.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderDaoStub implements OrderDao {

    private Order testOrder;

    public OrderDaoStub() {
        testOrder = new Order();
        testOrder.setOrderDate(new Date(2000, Calendar.FEBRUARY,1));
        testOrder.setOrderNumber(1);
        testOrder.setCustomerName("TestCustomer");
        testOrder.setProductType("TestProduct");
        testOrder.setArea(new BigDecimal("100"));
        testOrder.setCostPerSquareFoot(new BigDecimal("1"));
        testOrder.setLabourCostPerSquareFoot(new BigDecimal("2"));
        testOrder.setState("TestState");
        testOrder.setTaxRate(new BigDecimal("5"));
    }

    public OrderDaoStub (Order testOrder) {
        this.testOrder = testOrder;
    }

    /*
    @Override
    public int getNextOrderNumber() {
        return testOrder.getOrderNumber();
    }
     */

    @Override
    public Order addOrder(Order order) {
        if (order.getOrderDate().equals(testOrder.getOrderDate())
        && order.getOrderNumber() == testOrder.getOrderNumber()) {
            return testOrder;
        }
        return null;
    }

    @Override
    public Order getOrder(Date orderDate, int orderNumber) {
        if (orderDate.equals(testOrder.getOrderDate())
                && orderNumber == testOrder.getOrderNumber()) {
            return testOrder;
        }
        return null;
    }

    @Override
    public Order editOrder(Date orderDate, int orderNumber, Order newOrder) {
        if (orderDate.equals(testOrder.getOrderDate())
                && orderNumber == testOrder.getOrderNumber()) {
            testOrder = newOrder;
            return testOrder;
        }
        return null;
    }

    @Override
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order());
        return orders;
    }

    @Override
    public Order removeOrder(Date orderDate, int orderNumber) {
        if (orderDate.equals(testOrder.getOrderDate())
                && orderNumber == testOrder.getOrderNumber()) {
            Order temp = testOrder;
            testOrder = null;
            return temp;
        }
        return null;
    }
}
