package org.WileyEdgeCorp.FlooringMastery.dao;

import org.WileyEdgeCorp.FlooringMastery.dto.Order;
import org.WileyEdgeCorp.FlooringMastery.exceptions.PersistenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OrderDaoFileImplTest {

    /* test plan

    add order
    orderDao.addOrder(newOrder) -> newOrder
    orderDao.getOrder(newOrder) -> newOrder

    edit order
    orderDao.addOrder(newOrder)
    orderDao.editOrder(newOrder*) -> newOrder*
    orderDao.getOrder(newOrder) -> newOrder*

    edit order not exist
    orderDao.editOrder(newOrder) -> null
    orderDao.getOrder(newOrder) -> null

    get orders
    orderDao.addOrder(newOrder)
    orderDao.getOrders() -> newOrder

    remove order
    orderDao.addOrder(newOrder)
    orderDao.removeOrder(newOrder) -> newOrder
    orderDao.getOrder(newOrder) -> null

    remove order not exist
    orderDao.removeOrder(newOrder) -> null

     */

    private OrderDao orderDao;
    private Order newOrder;
    private Date newOrderDate;
    private int newOrderNumber;
    //private String testFile = "Orders_MMDDYYYY.txt";
    // yyyy = 2000
    // MM = 01
    // dd = 01
    private String testFile = "Orders_01012000.txt";
    private Date testDate = new SimpleDateFormat("yyyyMMdd").parse("20000101");
    private String newOrderString = """
            OrderNumber::CustomerName::State::TaxRate::ProductType::Area::CostPerSquareFoot::LaborCostPerSquareFoot::MaterialCost::LaborCost::Tax::Total
            1::testCustomer::TS::5::testProduct::100::1::2::100::200::15::315""";

    public OrderDaoFileImplTest() throws ParseException {
    }

    @BeforeEach
    public void setUp() throws IOException, ParseException, PersistenceException {
        // create dao
        orderDao = new OrderDaoFileImpl(testDate);

        // create blank test file
        new FileWriter(testFile);

        // create new order
        newOrder = new Order();
        newOrderDate = testDate;
        newOrderNumber = 1;
        newOrder.setOrderDate(newOrderDate);
        newOrder.setOrderNumber(newOrderNumber);
        newOrder.setCustomerName("testCustomer");
        newOrder.setState("TS");
        newOrder.setTaxRate(new BigDecimal("5"));
        newOrder.setProductType("testProduct");
        newOrder.setArea(new BigDecimal("100"));
        newOrder.setCostPerSquareFoot(new BigDecimal("1"));
        newOrder.setLabourCostPerSquareFoot(new BigDecimal("2"));
    }

    @Test
    public void addOrderTest(){

        Order added = orderDao.addOrder(newOrder);
        Order got = orderDao.getOrder(newOrderDate,newOrderNumber);

        Assertions.assertEquals(newOrder, added);
        Assertions.assertEquals(newOrder, got);

    }

    @Test
    public void editOrderTest(){
        Order added = orderDao.addOrder(newOrder);

        newOrder.setProductType("newTestProduct");

        Order edited = orderDao.editOrder(newOrderDate,newOrderNumber,newOrder);
        Order got = orderDao.getOrder(newOrderDate,newOrderNumber);

        Assertions.assertEquals(newOrder, edited);
        Assertions.assertEquals(newOrder, got);

    }

    @Test
    public void editOrderNotExistTest(){

        Order edited = orderDao.editOrder(newOrderDate,newOrderNumber,newOrder);
        Order got = orderDao.getOrder(newOrderDate,newOrderNumber);

        Assertions.assertNull(edited);
        Assertions.assertNull(got);

    }

    @Test
    public void getOrdersTest(){

        Order added = orderDao.addOrder(newOrder);
        List<Order> got = orderDao.getOrders();

        Assertions.assertEquals(newOrder, got.get(0));

    }

    @Test
    public void removeOrderTest(){
        Order added = orderDao.addOrder(newOrder);

        Order removed = orderDao.removeOrder(newOrderDate,newOrderNumber);
        Order got = orderDao.getOrder(newOrderDate,newOrderNumber);

        Assertions.assertEquals(newOrder, removed);
        Assertions.assertNull(got);

    }

    @Test
    public void removeOrderNotExistTest(){

        Order removed = orderDao.removeOrder(newOrderDate,newOrderNumber);

        Assertions.assertNull(removed);

    }

}
