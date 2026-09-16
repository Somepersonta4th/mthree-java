package org.WileyEdgeCorp.FlooringMastery.ui;

public class View {
    private UserIO IO;

    public int showMenuAndGetSelection() {
        IO.print("""
                  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                  * <<Flooring Program>>
                  * 1. Display Orders
                  * 2. Add an Order
                  * 3. Edit an Order
                  * 4. Remove an Order
                  * 5. Export All Data
                  * 6. Quit
                  *
                  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                """);
        return IO.readInt("Enter Selection:");
    }
}
