package org.WileyEdgeCorp.FlooringMastery.dao;

import org.WileyEdgeCorp.FlooringMastery.dto.Order;
import org.WileyEdgeCorp.FlooringMastery.exceptions.PersistenceException;

import java.io.*;
import java.math.BigDecimal;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class OrderDaoFileImpl implements OrderDao {

    private Date currentDate;
    
    private String FILE_PATH = "src/main/java/org/WileyEdgeCorp/FlooringMastery/Data/Orders/";
    private String FILE_PARAMETERS = "OrderNumber::CustomerName::State::TaxRate::ProductType::Area::CostPerSquareFoot::LaborCostPerSquareFoot::MaterialCost::LaborCost::Tax::Total";
    private final String DELIMITER = "::";
    private final Format DATE_FORMATER = new SimpleDateFormat("MMddyyyy");
    
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
    public Order getOrder(int orderNumber) {
        return ordersForCurrentDate.get(orderNumber);
    }

    @Override
    public Order editOrder(Order newOrder) {
        return ordersForCurrentDate.put(newOrder.getOrderNumber(),newOrder);
    }

    @Override
    public List<Order> getOrders() {
        return ordersForCurrentDate.values().stream().toList();
    }

    @Override
    public Order removeOrder(int orderNumber) {
        inUseOrderNumbers.remove(orderNumber);
        return ordersForCurrentDate.remove(orderNumber);
    }

    @Override
    public void loadDate(Date date) throws PersistenceException {
        if (date.equals(currentDate)) {
            return;
        }
        this.currentDate = date;

        String fileName = "Orders_" + DATE_FORMATER.format(date) + ".txt";

        ordersForCurrentDate = loadOrdersFromFileName(fileName);

    }

    // get all in-use order numbers. does not store orders into memory
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

        // skim each date file for its order numbers
        Arrays.stream(dateFiles).sequential()

                //filter by name
                .filter(file -> filePattern.matcher(file.getName()).find())

                //load file
                .forEach(file -> {

                    // load order numbers
                    Set<Integer> orderNumbers;
                    Date date;
                    try {
                        orderNumbers = loadOrderNumbersForDate(file.getName());
                        date = new SimpleDateFormat("MMddyyyy").parse(file.getName()
                                .replace("Orders_", "")
                                .replace(".txt", ""));
                    } catch (Exception e) {
                        //could not load file
                        return;
                    }

                    // update inUseOrderNumbers

                    for (int number : orderNumbers) {
                        inUseOrderNumbers.put(number,date);
                    }
                });

        return inUseOrderNumbers;
    }

    @Override
    public void exportData() throws PersistenceException {

        try {
            String fileName = FILE_PATH + "Orders_" + DATE_FORMATER.format(currentDate) + ".txt";
            PrintWriter writter = new PrintWriter(new FileWriter(fileName));

            //write parameters line
            writter.println(FILE_PARAMETERS);

            // write order on each line
            Set<Integer> orderNumbers = ordersForCurrentDate.keySet();
            for (int number : orderNumbers) {
                writter.println(marchallOrder(ordersForCurrentDate.get(number)));
            }

            writter.flush();
            writter.close();

        } catch (IOException e) {
            throw new PersistenceException("Error writing to file",e);
        }

    }

    private Set<Integer> loadOrderNumbersForDate(String filename) throws PersistenceException {
        Map<Integer, Order> orders = loadOrdersFromFileName(filename);

        return orders.keySet();
    }

    private Map<Integer,Order> loadOrdersFromFileName(String fileName) throws PersistenceException {
        Scanner scanner = getFileScanner(FILE_PATH + fileName);

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
            currentOrder.setOrderDate(currentDate);

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
    
    private String marchallOrder (Order order) {
        // OrderNumber::CustomerName::State::TaxRate::ProductType::Area::CostPerSquareFoot::LaborCostPerSquareFoot::MaterialCost::LaborCost::Tax::Total
        String out = "";
        
        out += order.getOrderNumber() + DELIMITER;
        out += order.getCustomerName() + DELIMITER;
        out += order.getState() + DELIMITER;
        out += order.getTaxRate() + DELIMITER;
        out += order.getProductType() + DELIMITER;
        out += order.getArea() + DELIMITER;
        out += order.getCostPerSquareFoot() + DELIMITER;
        out += order.getLabourCostPerSquareFoot() + DELIMITER;
        out += order.getMaterialCost() + DELIMITER;
        out += order.getLabourCost() + DELIMITER;
        out += order.getTax() + DELIMITER;
        out += order.getTotal();
        
        return out;
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
