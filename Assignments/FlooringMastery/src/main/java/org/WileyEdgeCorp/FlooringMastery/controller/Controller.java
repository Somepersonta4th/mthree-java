package org.WileyEdgeCorp.FlooringMastery.controller;

import jdk.jshell.spi.ExecutionControl;
import org.WileyEdgeCorp.FlooringMastery.service.ServiceLayer;
import org.WileyEdgeCorp.FlooringMastery.ui.View;

public class Controller {
    private ServiceLayer service;
    private View view;

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
                default:
                    break;
            }
            isRunning = false;
        }
    }

    public int showMenuAndGetSelection () {
        return view.showMenuAndGetSelection();
    }
}
