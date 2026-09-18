package org.WileyEdgeCorp.FlooringMastery.service;

import org.WileyEdgeCorp.FlooringMastery.dao.*;
import org.WileyEdgeCorp.FlooringMastery.dto.Order;
import org.WileyEdgeCorp.FlooringMastery.dto.Product;
import org.WileyEdgeCorp.FlooringMastery.dto.Tax;

import java.util.Date;
import java.util.List;

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
    public Order addOrder(Order order) {
        return null;
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
    public List<Tax> getTaxes() {
        return List.of();
    }

    @Override
    public List<Product> getProducts() {
        return List.of();
    }
}
