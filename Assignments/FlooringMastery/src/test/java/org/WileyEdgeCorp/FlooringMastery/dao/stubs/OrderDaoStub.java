package org.WileyEdgeCorp.FlooringMastery.dao.stubs;

import org.WileyEdgeCorp.FlooringMastery.dao.OrderDao;
import org.WileyEdgeCorp.FlooringMastery.dto.Order;
import org.WileyEdgeCorp.FlooringMastery.exceptions.PersistenceException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderDaoStub implements OrderDao {

    private Order testOrder;

    public OrderDaoStub() throws ParseException {
        testOrder = new Order();
        Date date = new SimpleDateFormat("yyyyMMdd").parse("20000101");
        testOrder.setOrderDate(date);
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
        if (!(order.getOrderDate().equals(testOrder.getOrderDate())
        && order.getOrderNumber() == testOrder.getOrderNumber())) {
            return order;
        }
        return null;
    }

    @Override
    public Order getOrder(int orderNumber) {
        if (testOrder == null) {
            return null;
        }
        if (orderNumber == testOrder.getOrderNumber()) {
            return testOrder;
        }
        return null;
    }

    @Override
    public Order editOrder(Order newOrder) {
        if (newOrder.getOrderDate().equals(testOrder.getOrderDate())
                && newOrder.getOrderNumber() == testOrder.getOrderNumber()) {
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
    public Order removeOrder(int orderNumber) {
        if (testOrder == null) {
            return null;
        }
        if (orderNumber == testOrder.getOrderNumber()) {
            Order temp = testOrder;
            testOrder = null;
            return temp;
        }
        return null;
    }

    @Override
    public void loadDate(Date date) throws PersistenceException {

    }

    @Override
    public Map<Integer, Date> loadOrderNumbers() throws PersistenceException {
        Map<Integer, Date> map = new HashMap<>();
        map.put(testOrder.getOrderNumber(),testOrder.getOrderDate());
        return map;
    }

    @Override
    public void exportData() throws PersistenceException {

    }
}
