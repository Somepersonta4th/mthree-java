package ui;

import dto.VendingItem;

import java.util.ArrayList;

public class VendingView {

    UserIO io;

    public VendingView () {}

    public VendingView(UserIO io) {
        this.io = io;
    }

    public void displayItems (ArrayList<VendingItem> items) {
        io.print("Available items:");
        for (int i = 0; i < items.size(); i++) {
            VendingItem item = items.get(i);
            io.print(i + ". " + item.toString());
        }
    }

    public int getItemSelection () {
        return io.readInt("Enter desired item number:");
    }

}
