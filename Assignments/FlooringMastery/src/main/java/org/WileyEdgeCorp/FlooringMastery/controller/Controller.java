package org.WileyEdgeCorp.FlooringMastery.controller;

import jdk.jshell.spi.ExecutionControl;
import org.WileyEdgeCorp.FlooringMastery.service.ServiceLayer;
import org.WileyEdgeCorp.FlooringMastery.ui.View;

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
        while (isRunning){
            switch (showMenuAndGetSelection()) {
                case 1:
                    throw new UnsupportedOperationException("");
                case 2:
                    throw new UnsupportedOperationException("");
                case 3:
                    throw new UnsupportedOperationException("");
                case 4:
                    throw new UnsupportedOperationException("");
                case 5:
                    throw new UnsupportedOperationException("");
                case 6:
                    isRunning = false;
                    break;
                default:
                    throw new UnsupportedOperationException("");
            }
        }
        exitMessage();
    }

    private void exitMessage() {
        view.displayExitMessage();
    }

    public int showMenuAndGetSelection () {
        return view.showMenuAndGetSelection();
    }
}
