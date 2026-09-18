package org.WileyEdgeCorp.FlooringMastery.controller;

import org.WileyEdgeCorp.FlooringMastery.dto.Order;
import org.WileyEdgeCorp.FlooringMastery.dto.Product;
import org.WileyEdgeCorp.FlooringMastery.dto.Tax;
import org.WileyEdgeCorp.FlooringMastery.service.ServiceLayer;
import org.WileyEdgeCorp.FlooringMastery.ui.View;

import java.util.List;

public class Controller {
    private ServiceLayer service;
    private View view;

    public Controller() {
    }

    public Controller(ServiceLayer service, View view) {
        this.service = service;
        this.view = view;
    }

    public void run () {
        boolean isRunning = true;

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
                    isRunning = false;
                    break;
                default:
                    unknownSelection();
            }
        }
        //finished
        exitMessage();
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

        List<Product> products = service.getProducts();
        if (products.isEmpty()) {
            view.displayNoProductsMessage();
            return;
        }
        List<Tax> taxes = service.getTaxes();
        if (products.isEmpty()) {
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
        Order added = service.addOrder(newOrder);
        if (added == null) {
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
