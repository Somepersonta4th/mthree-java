package org.WileyEdgeCorp.FlooringMastery.ui;

import org.WileyEdgeCorp.FlooringMastery.dto.Order;
import org.WileyEdgeCorp.FlooringMastery.dto.Product;
import org.WileyEdgeCorp.FlooringMastery.dto.Tax;

import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class View {
    private UserIO IO;

    private final Format DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd");

    public View () {}

    public View(UserIO IO) {
        this.IO = IO;
    }

    public int showMenuAndGetSelection() {
        IO.print("""
                  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                  * <<Flooring Options>>
                  * Options for Selected date:
                  * 1. Display Orders
                  * 2. Add an Order
                  * 3. Edit an Order
                  * 4. Remove an Order
                  * 5. Export All Data
                  *
                  * 6. Export + Select New Date
                  *
                  * 7. Quit
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

        displayOrderInfo(newOrder);

        if (IO.readString("Please confirm you wish to add this order.\nType 'YES' to confirm:").equals("YES")) {
            return true;
        }
        return false;
    }

    private void displayOrderInfo(Order newOrder) {
        String orderInfo = "Date    :    " + DATE_FORMATER.format(newOrder.getOrderDate()) +
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
        for (int i = 0; i < products.size(); i++) {
            IO.print((i+1) + ".");
            Product product = products.get(i);
            IO.print("Product type : " + product.getProductType());
            IO.print("Material cost per square foot : $" + product.getCostPerSquareFoot());
            IO.print("Labour cost per square foot : $" + product.getLabourCostPerSquareFoot());
            IO.print("\n");
        };
    }

    //display tax for each state in list
    public void displayTaxes (List<Tax> taxes) {
        for (int i = 0; i < taxes.size(); i++) {
            IO.print((i+1) + ".");
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
        newOrder.setOrderNumber(IO.readInt("Order number:",0));
        newOrder.setCustomerName(IO.readString("Customer name:"));
        newOrder.setArea(IO.readBigDecimal("Square foot area required:"));

        //min area
        if (newOrder.getArea().doubleValue() < 100) {
            IO.print("Minium area of 100 square feet. Area set to 100 square feet.");
            newOrder.setArea(new BigDecimal("100"));
            IO.readString("Hit enter to continue");
        }

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

    public void displayNoProductsMessage() {IO.readString("Error: no product data loaded. Hit enter to continue");}

    public void displayNoTaxesMessage() {IO.readString("Error: no state data loaded. Hit enter to continue");}

    public void displayOrderNumbers(Map<Integer, Date> inUseOrderNumbers) {
        if (inUseOrderNumbers.isEmpty()) {
            IO.print("No orders on record.");
            return;
        }
        IO.print("All orders on record:");
        for (int orderNumber : inUseOrderNumbers.keySet()) {
            IO.print("Order number - " + orderNumber + "            Order date - " + DATE_FORMATER.format(inUseOrderNumbers.get(orderNumber)));
        }
    }

    public Date getNewDate() {
        return IO.readDate("Please enter the date you wish to select and export current data. Enter blank to cancel.");
    }

    public void displayDateCannotBeNullMessage() {IO.print("Initial date cannot be blank.");}

    public void displayFailedToExportMessage() {
        IO.print("Error occurred while exporting data. Please try again.");
        IO.readString("Hit enter to continue...");
    }

    public void displayUnknownSelectionMessage() {IO.print("Unrecognised option. Please try again.");}

    public void displayNewDateMessage(Date inUseDate) {
        IO.print("New date loaded : " + DATE_FORMATER.format(inUseDate));
    }

    public int getOrder() {
        return IO.readInt("Enter the number of the order you wish to select:");
    }

    public void displayNoOrderMessage() {
        IO.readString("No such order found on the current date. Hit enter to continue.");
    }

    public void displayOrder(Order order) {
        IO.print("Selected Order:");
        displayOrderInfo(order);
    }

    public boolean getVerifyRemove() {
        return IO.readString("Please confirm you wish to remove this order.\nType 'YES' to confirm:").equals("YES");
    }

    public void displayNotRemovingOrderMessage() {IO.readString("Canceled remove order. Hit enter to continue...");}

    public void displayOrderRemovedMessage() {IO.readString("Order removed successfully. Hit enter to continue..");}

    public Order editOrder(Order order, List<Product> products, List<Tax> taxes) {
        IO.readString("""
    For each property of the selected order, the current value will be displayed.
    Hit enter to keep current value.
    Type in new value to change.
    Hit enter to begin...""");

        // only set as new value if not empty

        String customerName = IO.readString(" Current customer name : " + order.getCustomerName() + "\nNew name :");
        if (!customerName.isEmpty()) {
            order.setCustomerName(customerName);
        }

        BigDecimal area = IO.readBigDecimal("Current area : " + order.getArea() + "\nNew area :",true);
        if (!(area == null)) {

            // handle minimum area
            if (area.doubleValue() < 100) {
                IO.print("Minium area of 100 square feet. Area set to 100 square feet.");
                order.setArea(new BigDecimal("100"));
                IO.readString("Hit enter to continue");

            } else {
                order.setArea(area);
            }

        }

        //show products and get selection
        IO.print("\nAvailable products\n");
        displayProducts(products);

        // gives -1 if empty
        int productNum = IO.readInt("Current product : " + order.getProductType() + "\nNew product :",1,products.size(),true);
        if (productNum != -1) {
            Product product = products.get(productNum-1);
            order.setProductType(product.getProductType());
            order.setCostPerSquareFoot(product.getCostPerSquareFoot());
            order.setLabourCostPerSquareFoot(product.getLabourCostPerSquareFoot());
        }

        //show states and get selection
        IO.print("\nAvailable states\n");
        displayTaxes(taxes);

        // gives -1 if empty
        int taxNum = IO.readInt("Current state : " + order.getProductType() + "\nNew state :",1,taxes.size(),true);
        if (taxNum != -1) {
            Tax tax = taxes.get(taxNum-1);
            order.setState(tax.getState());
            order.setTaxRate(tax.getTaxRate());
        }

        displayOrderInfo(order);

        return order;

    }

    public void displayOrders(List<Order> orders) {
        IO.print("Orders for this date:");
        orders.forEach(order -> displayOrderInfo(order));
    }
}
