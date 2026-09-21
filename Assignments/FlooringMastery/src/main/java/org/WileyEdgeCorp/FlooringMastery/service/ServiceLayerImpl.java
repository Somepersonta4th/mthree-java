package org.WileyEdgeCorp.FlooringMastery.service;

import org.WileyEdgeCorp.FlooringMastery.dao.*;
import org.WileyEdgeCorp.FlooringMastery.dto.Order;
import org.WileyEdgeCorp.FlooringMastery.dto.Product;
import org.WileyEdgeCorp.FlooringMastery.dto.Tax;
import org.WileyEdgeCorp.FlooringMastery.exceptions.DataCollisionException;
import org.WileyEdgeCorp.FlooringMastery.exceptions.NoDataLoaded;
import org.WileyEdgeCorp.FlooringMastery.exceptions.NoSuchOrderException;
import org.WileyEdgeCorp.FlooringMastery.exceptions.PersistenceException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ServiceLayerImpl implements ServiceLayer {

    private OrderDao orderDao;
    private ProductDao productDao;
    private TaxDao taxDao;

    public ServiceLayerImpl () {}

    public ServiceLayerImpl(OrderDao orderDao, ProductDao productDao, TaxDao taxDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
    }

    /*
    @Override
    public int getNextOrderNumber() {
        return 0;
    }
     */

    @Override
    public Order addOrder(Order order) throws DataCollisionException {
        Order added = orderDao.addOrder(order);
        if (added == null) {
            throw new DataCollisionException("order number already in use");
        }
        return orderDao.addOrder(order);
    }

    @Override
    public List<Order> getOrders() {
        return orderDao.getOrders();
    }

    @Override
    public Order getOrder(int orderNumber) {
        return orderDao.getOrder(orderNumber);
    }

    @Override
    public Order editOrder(Order newOrder) throws NoSuchOrderException {
        Order edited = orderDao.editOrder(newOrder);
        if (edited == null) {
            throw new NoSuchOrderException("No corresponding order number found in this date.");
        }
        return edited;
    }

    @Override
    public Order removeOrder(int orderNumber) {
        return orderDao.removeOrder(orderNumber);
    }

    @Override
    public void exportData() throws PersistenceException {
        orderDao.exportData();
    }

    @Override
    public List<Tax> getTaxes() throws NoDataLoaded {
        List<Tax> taxes = taxDao.getAllTaxes();
        if (taxes.isEmpty()) {
            throw new NoDataLoaded("no taxes loaded");
        }
        return taxes;
    }

    @Override
    public List<Product> getProducts() throws NoDataLoaded {
        List<Product> products = productDao.getAllProducts();
        if (products.isEmpty()) {
            throw new NoDataLoaded("no products loaded");
        }
        return products;
    }

    @Override
    public Map<Integer, Date> loadOrderNumbers() throws PersistenceException {
        return orderDao.loadOrderNumbers();
    }

    @Override
    public void loadDate(Date dateToUse) throws PersistenceException {
        orderDao.loadDate(dateToUse);
    }
}
