package org.WileyEdgeCorp.FlooringMastery.controller;

import org.WileyEdgeCorp.FlooringMastery.exceptions.DataCollisionException;
import org.WileyEdgeCorp.FlooringMastery.exceptions.NoDataLoaded;
import org.WileyEdgeCorp.FlooringMastery.dto.Order;
import org.WileyEdgeCorp.FlooringMastery.dto.Product;
import org.WileyEdgeCorp.FlooringMastery.dto.Tax;
import org.WileyEdgeCorp.FlooringMastery.exceptions.NoSuchOrderException;
import org.WileyEdgeCorp.FlooringMastery.exceptions.PersistenceException;
import org.WileyEdgeCorp.FlooringMastery.service.ServiceLayer;
import org.WileyEdgeCorp.FlooringMastery.ui.View;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Controller {
    private ServiceLayer service;
    private View view;

    private Date inUseDate;

    public Controller() {
    }

    public Controller(ServiceLayer service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run () throws PersistenceException {
        boolean isRunning = true;

        onStart();

        //main loop
        while (isRunning){

            //main menu
            switch (showMenuAndGetSelection()) {
                case 1:
                    displayOrder();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    exportData();
                    break;
                case 6:
                    changeDate();
                    break;
                case 7:
                    isRunning = false;
                    break;
                default:
                    unknownSelection();
            }
        }
        //finished
        exitMessage();
    }

    private void onStart() throws PersistenceException {
        // load and show order numbers for each date
        Map<Integer,Date> inUseOrderNumbers = null;
        try {
            inUseOrderNumbers = service.loadOrderNumbers();
        } catch (PersistenceException e) {
            throw new PersistenceException("Fatal: fatal error when loading folder. Could not access folder.",e);
        }
        view.displayOrderNumbers(inUseOrderNumbers);

        changeDate();
        while (inUseDate == null) {
            view.displayDateCannotBeNullMessage();
            changeDate();
        }

    }

    private void changeDate() throws PersistenceException {

        // select date to use
        Date newDate = view.getNewDate();

        // return if date unchanged
        if (newDate == null
        || newDate.equals(inUseDate)) {
            return;
        }

        // save data for current date
        if (inUseDate != null) {
            exportData();
        }

        // load data for date
        inUseDate = newDate;
        try {
            service.loadDate(inUseDate);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fatal: fatal error when loading date file. Either file cannot be created or file exists but may be malformed.", e);
        }

        view.displayNewDateMessage(inUseDate);
    }

    private void unknownSelection() {
        view.displayUnknownSelectionMessage();
    }

    private void exportData() {
        try {
            service.exportData();
        } catch (PersistenceException e) {
            view.displayFailedToExportMessage();
        }
    }

    private void removeOrder() {
        Order order = getOrder();
        if (order == null) {
            return;
        }

        view.displayOrder(order);

        // verify remove order
        if (!view.getVerifyRemove()) {
            view.displayNotRemovingOrderMessage();
            return;
        }

        Order removed = service.removeOrder(order.getOrderNumber());

        // check order was removed
        if (removed == null) {
            view.displayNoOrderMessage();
            return;
        }

        view.displayOrderRemovedMessage();

    }

    private void editOrder() {
        Order order = getOrder();
        if (order == null) {
            return;
        }

        //verity products and taxes available
        List<Product> products;
        try {
            products = service.getProducts();
        } catch (NoDataLoaded e) {
            view.displayNoProductsMessage();
            return;
        }

        List<Tax> taxes;
        try {
            taxes = service.getTaxes();
        } catch (NoDataLoaded e) {
            view.displayNoTaxesMessage();
            return;
        }

        // update order
        order = view.editOrder(order,products,taxes);

        try {
            service.editOrder(order);
        } catch (NoSuchOrderException e) {
            view.displayNoOrderMessage();
        }

    }

    private void addOrder() {
        view.displayAddOrderBanner();

        //verity products and taxes available
        List<Product> products = null;
        try {
            products = service.getProducts();
        } catch (NoDataLoaded e) {
            view.displayNoProductsMessage();
            return;
        }

        List<Tax> taxes;
        try {
            taxes = service.getTaxes();
        } catch (NoDataLoaded e) {
            view.displayNoTaxesMessage();
            return;
        }

        //get properties from user
        Order newOrder = view.createOrder(products,taxes);
        newOrder.setOrderDate(inUseDate);

        //confirm order details
        boolean confirmAdd = view.confirmAddOrder(newOrder);
        if (!confirmAdd) {
            //do not add order
            view.displayNotAddingOrderMessage();
            return;
        }

        //add order
        view.displayAddingOrderMessage();
        Order added;

        try {
            added = service.addOrder(newOrder);
        } catch (DataCollisionException e) {
            //order number already exist
            view.displayFailedToAddObjectMessage();
            return;
        }

        view.displaySuccessfullyAddedObjectMessage();
    }

    private void displayOrder() {
        List<Order> orders = service.getOrders();
        view.displayOrders(orders);
    }

    // get order number from user then return order
    private Order getOrder() {
        int orderNum = view.getOrder();

        Order order;
        try {
            order = service.getOrder(orderNum);
        } catch (NoSuchOrderException e) {
            view.displayNoOrderMessage();
            return null;
        }

        return order;
    }

    private void exitMessage() {
        view.displayExitMessage();
    }

    public int showMenuAndGetSelection () {
        return view.showMenuAndGetSelection();
    }
}
