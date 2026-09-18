package org.WileyEdgeCorp.FlooringMastery.ui;

import org.WileyEdgeCorp.FlooringMastery.dto.Order;
import org.WileyEdgeCorp.FlooringMastery.dto.Product;
import org.WileyEdgeCorp.FlooringMastery.dto.Tax;

import java.math.BigDecimal;
import java.util.List;

public class View {
    private UserIO IO;

    public View () {}

    public View(UserIO IO) {
        this.IO = IO;
    }

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

    public void displayExitMessage() {
        IO.print("Quiting...");
    }

    public void displayAddOrderBanner() {IO.print("=== Add Order ===");}

    public void displayNotAddingOrderMessage() {IO.readString("Canceling add order. Hit enter to continue");}

    public void displayAddingOrderMessage() {IO.print("Adding order...");}

    public void displayFailedToAddObjectMessage() {
        IO.readString("""
        Failed to add order. Another order shares the same order number.\s
        Hit enter to continue""");
    }

    public void displaySuccessfullyAddedObjectMessage() {IO.readString("Added order successfully. Hit enter to continue");}

    public boolean confirmAddOrder(Order newOrder) {
        IO.print("The order you have created is...\n");

        displayOrder(newOrder);

        if (IO.readString("Please confirm you wish to add this order.\nType 'YES' to confirm:").equals("YES")) {
            return true;
        }
        return false;
    }

    private void displayOrder(Order newOrder) {
        String orderInfo = "Date    :    " + newOrder.getOrderDate() +
                "\n Order Number - " + newOrder.getOrderNumber() +
                "       Customer - " + newOrder.getCustomerName() +
                "\n\n Material : " + newOrder.getProductType() +
                "\n $" + newOrder.getCostPerSquareFoot() + " per square foot in material" +
                "       $" + newOrder.getLabourCostPerSquareFoot() + " per square foot in labour" +
                "\n\n Area : " + newOrder.getArea() + " square feet" +
                "\n $" + newOrder.getMaterialCost() + " total for materials" +
                "       $" + newOrder.getLabourCost() + " total for labour" +
                "\n\nJob is in " + newOrder.getState() + 
                " with a " + (newOrder.getTaxRate()) 
                + "% tax rate, resulting in $" + newOrder.getTax() + " in tax" +
                "\n\nTotal price : $" + newOrder.getTotal();
        IO.print(orderInfo);
    }

    //display each product in list
    public void displayProducts (List<Product> products) {
        for (int i = 0; i < products.size();) {
            IO.print(++i + ".");
            Product product = products.get(i);
            IO.print("Product type : " + product.getProductType());
            IO.print("Material cost per square foot : $" + product.getCostPerSquareFoot());
            IO.print("Labour cost per square foot : $" + product.getLabourCostPerSquareFoot());
            IO.print("\n");
        };
    }

    //display tax for each state in list
    public void displayTaxes (List<Tax> taxes) {
        for (int i = 0; i < taxes.size();) {
            IO.print(++i + ".");
            Tax tax = taxes.get(i);
            IO.print("State : " + tax.getState());
            IO.print("State tax rate : $" + tax.getTaxRate() + "%");
            IO.print("\n");
        };
    }

    //create new order -> display products and states -> set order properties
    public Order createOrder(List<Product> products, List<Tax> taxes) {
        Order newOrder = new Order();

        //get order info
        newOrder.setOrderDate(IO.readDate("What date is the order for?"));
        newOrder.setOrderNumber(IO.readInt("Order number:",0));
        newOrder.setCustomerName(IO.readString("Customer name:"));
        newOrder.setArea(IO.readBigDecimal("Square foot area required:"));

        //show products and get selection
        IO.print("\nAvailable products\n");
        displayProducts(products);
        Product product = products.get(IO.readInt("Select product:",1,products.size())-1);
        newOrder.setProductType(product.getProductType());
        newOrder.setCostPerSquareFoot(product.getCostPerSquareFoot());
        newOrder.setLabourCostPerSquareFoot(product.getLabourCostPerSquareFoot());

        //show states and get selection
        IO.print("\nAvailable states\n");
        displayTaxes(taxes);
        Tax tax = taxes.get(IO.readInt("Select state:",1,taxes.size())-1);
        newOrder.setState(tax.getState());
        newOrder.setTaxRate(tax.getTaxRate());

        return newOrder;
    }

    public void displayNoProductsMessage() {IO.print("Error: no product data loaded.");}

    public void displayNoTaxesMessage() {IO.print("Error: no state data loaded.");}
}
