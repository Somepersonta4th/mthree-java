package org.WileyEdgeCorp.FlooringMastery.controller;

import org.WileyEdgeCorp.FlooringMastery.exceptions.DataCollisionException;
import org.WileyEdgeCorp.FlooringMastery.exceptions.NoDataLoaded;
import org.WileyEdgeCorp.FlooringMastery.dto.Order;
import org.WileyEdgeCorp.FlooringMastery.dto.Product;
import org.WileyEdgeCorp.FlooringMastery.dto.Tax;
import org.WileyEdgeCorp.FlooringMastery.exceptions.PersistenceException;
import org.WileyEdgeCorp.FlooringMastery.service.ServiceLayer;
import org.WileyEdgeCorp.FlooringMastery.ui.View;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Controller {
    private ServiceLayer service;
    private View view;

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
    }

    private void changeDate() throws PersistenceException {
        // select date to use
        Date dateToUse = view.getDate();
        // load data for date
        try {
            service.loadDate(dateToUse);
        } catch (PersistenceException e) {
            throw new PersistenceException("Fatal: fatal error when loading date file. File may be malformed.", e);
        }
    }

    private void unknownSelection() {
        throw new UnsupportedOperationException("");
    }

    private void exportData() {
        throw new UnsupportedOperationException("");
    }

    private void removeOrder() {
        throw new UnsupportedOperationException("");
    }

    private void editOrder() {
        throw new UnsupportedOperationException("");
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
        throw new UnsupportedOperationException("");
    }

    private void exitMessage() {
        view.displayExitMessage();
    }

    public int showMenuAndGetSelection () {
        return view.showMenuAndGetSelection();
    }
}
