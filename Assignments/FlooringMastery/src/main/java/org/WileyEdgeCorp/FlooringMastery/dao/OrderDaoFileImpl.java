package org.WileyEdgeCorp.FlooringMastery.dao;

import org.WileyEdgeCorp.FlooringMastery.dto.Order;
import org.WileyEdgeCorp.FlooringMastery.exceptions.PersistenceException;

import java.io.*;
import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class OrderDaoFileImpl implements OrderDao {

    private Date currentDate;
    private String FILE_PATH = "src/main/java/org/WileyEdgeCorp/FlooringMastery/Data/Orders/";
    private String FILE_PARAMETERS = "OrderNumber::CustomerName::State::TaxRate::ProductType::Area::CostPerSquareFoot::LaborCostPerSquareFoot::MaterialCost::LaborCost::Tax::Total";
    private final String DELIMITER = "::";
    // Map<order number , order>
    private Map<Integer,Order> ordersForCurrentDate;
    // Map<order number , date>
    private Map<Integer,Date> inUseOrderNumbers = new HashMap<>();

    public OrderDaoFileImpl() {}

    public OrderDaoFileImpl(Date currentDate) throws PersistenceException {
        loadDate(currentDate);
    }

    @Override
    public Order addOrder(Order order) {



        //check collision
        if (inUseOrderNumbers.get(order.getOrderNumber()) != null) {
            return null;
        }

        order.setOrderDate(currentDate);

        inUseOrderNumbers.put(order.getOrderNumber(),order.getOrderDate());
        ordersForCurrentDate.put(order.getOrderNumber(),order);
        return order;
    }

    @Override
    public Order getOrder(Date orderDate, int orderNumber) {
        return null;
    }

    @Override
    public Order editOrder(Date orderDate, int orderNumber, Order newOrder) {
        return null;
    }

    @Override
    public List<Order> getOrders() {
        return List.of();
    }

    @Override
    public Order removeOrder(Date orderDate, int orderNumber) {
        return null;
    }

    @Override
    public void loadDate(Date date) throws PersistenceException {
        if (date.equals(currentDate)) {
            return;
        }
        this.currentDate = date;

        Format dateFormaterForFile = new SimpleDateFormat("MMddyyyy");
        String fileName = FILE_PATH + "Orders_" + dateFormaterForFile.format(date) + ".txt";

        ordersForCurrentDate = loadOrdersFromFileName(fileName);

    }

    @Override
    public Map<Integer, Date> loadOrderNumbers() throws PersistenceException {
        File folder = new File(FILE_PATH);

        //get date files
        File[] dateFiles;
        try {
            dateFiles = folder.listFiles();
        } catch (Exception e) {
            throw new PersistenceException("Could not access orders folder",e);
        }

        if (dateFiles == null) {
            throw new PersistenceException("Orders folder does not exist", new Exception());
        }

        // regex filter for files
        Pattern filePattern = Pattern.compile("Orders_[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9].txt");

        Arrays.stream(dateFiles).sequential()

                //filter by name
                .filter(file -> filePattern.matcher(file.getName()).find())

                //load file
                .map(file -> {
                    try {
                        return loadOrdersFromFileName(file.getName());
                    } catch (Exception e) {
                        //could not load file
                        return null;
                    }
                })

                //remove not loaded files
                .filter(Objects::nonNull)

                //remove empty files
                .filter(file -> !file.isEmpty())

                //iterate through orders and put to inUseOrderNumbers
                .forEach(file -> {
                    for (int orderNumber : file.keySet()) {
                        inUseOrderNumbers.put(orderNumber,file.get(orderNumber).getOrderDate());
                    }
                });

        ;

        return Map.of();
    }

    private Map<Integer,Order> loadOrdersFromFileName(String fileName) throws PersistenceException {
        Scanner scanner = getFileScanner(fileName);

        //if no file exists
        if (scanner == null) {
            return new HashMap<>();
        }

        //remove first line
        try {
            String propertyNames = scanner.nextLine();
        } catch (NoSuchElementException e) {
            throw new PersistenceException("malformed order file",e);
        }

        //put each order
        Map<Integer,Order> orders = new HashMap<>();
        String currentLine;
        Order currentOrder;
        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentOrder = unmarshallOrder(currentLine);

            try {
                orders.put(currentOrder.getOrderNumber(),currentOrder);
            } catch (Exception ignored) {
                //skip malformed order
            };

        };

        return orders;
    }

    //load file for a given date. creates file if not exist
    private Scanner getFileScanner(String fileName) throws PersistenceException {

        // attempt to read date file
        try {

            return new Scanner(new BufferedReader(new FileReader(fileName)));

        } catch (FileNotFoundException e) {

            // no file found
            try {

                //create new date file
                new FileWriter(fileName).append(FILE_PARAMETERS).flush();
                ordersForCurrentDate = new HashMap<>();
                return null;

            } catch (IOException ex) {

                throw new PersistenceException ("Could not create new date file",ex);

            }
        }

    }

    private Order unmarshallOrder(String raw) throws PersistenceException {
        String[] properties = raw.split(this.DELIMITER);

        try {
            return createOrder(properties);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new PersistenceException("malformed order data", e);
        }
    }

    private static Order createOrder(String[] properties) {
        // OrderNumber::CustomerName::State::TaxRate::ProductType::Area::CostPerSquareFoot::LaborCostPerSquareFoot::MaterialCost::LaborCost::Tax::Total
        Order order = new Order();
        order.setOrderNumber(Integer.parseInt(properties[0]));
        order.setCustomerName(properties[1]);
        order.setState(properties[2]);
        order.setTaxRate(new BigDecimal(properties[3]));
        order.setProductType(properties[4]);
        order.setArea(new BigDecimal(properties[5]));
        order.setCostPerSquareFoot(new BigDecimal(properties[6]));
        order.setLabourCostPerSquareFoot(new BigDecimal(properties[7]));
        return order;
    }

}
