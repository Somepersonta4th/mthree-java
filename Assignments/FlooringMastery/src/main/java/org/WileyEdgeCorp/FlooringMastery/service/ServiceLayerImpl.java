package org.WileyEdgeCorp.FlooringMastery.service;

import org.WileyEdgeCorp.FlooringMastery.dao.*;
import org.WileyEdgeCorp.FlooringMastery.dto.Order;
import org.WileyEdgeCorp.FlooringMastery.dto.Product;
import org.WileyEdgeCorp.FlooringMastery.dto.Tax;
import org.WileyEdgeCorp.FlooringMastery.exceptions.DataCollisionException;
import org.WileyEdgeCorp.FlooringMastery.exceptions.NoDataLoaded;
import org.WileyEdgeCorp.FlooringMastery.exceptions.PersistenceException;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ServiceLayerImpl implements ServiceLayer {

    private ExportDao exportDao;
    private OrderDao orderDao;
    private ProductDao productDao;
    private TaxDao taxDao;

    public ServiceLayerImpl () {}

    public ServiceLayerImpl(ExportDao exportDao, OrderDao orderDao, ProductDao productDao, TaxDao taxDao) {
        this.exportDao = exportDao;
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
    public Order getOrder(Date date, int orderNumber) {
        return null;
    }

    @Override
    public Order editOrder(Date date, int orderNumber, Order newOrder) {
        return null;
    }

    @Override
    public Order removeOrder(Date date, int orderNumber) {
        return null;
    }

    @Override
    public void exportData() {}

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
