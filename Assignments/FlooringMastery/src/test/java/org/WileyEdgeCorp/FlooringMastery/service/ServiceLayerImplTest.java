package org.WileyEdgeCorp.FlooringMastery.service;

import org.WileyEdgeCorp.FlooringMastery.dao.ExportDao;
import org.WileyEdgeCorp.FlooringMastery.dao.OrderDao;
import org.WileyEdgeCorp.FlooringMastery.dao.ProductDao;
import org.WileyEdgeCorp.FlooringMastery.dao.TaxDao;
import org.WileyEdgeCorp.FlooringMastery.dto.Order;
import org.WileyEdgeCorp.FlooringMastery.dto.Product;
import org.WileyEdgeCorp.FlooringMastery.dto.Tax;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

class ServiceLayerImplTest {

    /* test plan

    get order
    * service.getOrder(testOrder) -> testOrder

    get order not exist
    * service.getOrder(fakeOrder) -> null

    add order
    * service.addOrder(newOrder) -> newOrder
    * service.getOrder(newOrder) -> newOrder

    add order collision
    * service.addOrder(testOrder) -> null

    edit order
    * service.editOrder(testOrder,newOrder) -> newOrder

    edit order not exist
    * service.editOrder(fakeOrder,newOrder) -> null

    remove order
    * service.removeOrder(testOrder) -> testOrder

    remove order not exist
    * service.removeOrder(fakeOrder) -> null

    export data

    get taxes
    * service.getTaxes -> test taxes

    get products
    * service.getProducts -> test products

     */

    private ExportDao exportDao;
    private OrderDao orderDao;
    private ProductDao productDao;
    private TaxDao taxDao;

    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
    ServiceLayer service;

    private Order newOrder = new Order();
    private Date newOrderDate;
    private int newOrderNumber = 2;

    @BeforeEach
    public void setUp() throws ParseException {

        //create service layer
        service = ctx.getBean("serviceLayer", ServiceLayer.class);

        //set up new order for order dao tests
        newOrderDate = new SimpleDateFormat("yyyyMMdd").parse("20000101");
        newOrder.setOrderDate(newOrderDate);
        newOrder.setOrderNumber(newOrderNumber);
        newOrder.setCustomerName("TestCustomer");
        newOrder.setProductType("TestProduct");
        newOrder.setArea(new BigDecimal("100"));
        newOrder.setCostPerSquareFoot(new BigDecimal("1"));
        newOrder.setLabourCostPerSquareFoot(new BigDecimal("2"));
        newOrder.setState("TestState");
        newOrder.setTaxRate(new BigDecimal("5"));

    }

    @Test
    void getOrderTest() throws ParseException {
        //target testOrder
        Date testDate = newOrderDate;
        int testNumber = 1;

        Order result = service.getOrder(testDate,testNumber);

        Assertions.assertNotNull(result);
    }

    @Test
    void getOrderNotExistTest() throws ParseException {
        //target non-existent order
        Date testDate = newOrderDate;
        int testNumber = 3;

        Order result = service.getOrder(testDate,testNumber);

        Assertions.assertNull(result);
    }

    @Test
    void getAddOrder() throws ParseException {
        //target newOrder
        Date testDate = newOrderDate;
        int testNumber = newOrderNumber;

        Order added = service.addOrder(newOrder);
        Order got = service.getOrder(testDate,testNumber);

        Assertions.assertEquals(added, got);
    }

    @Test
    void getAddOrderCollision() throws ParseException {
        //target testOrder
        newOrder.setOrderNumber(1);

        Order added = service.addOrder(newOrder);

        Assertions.assertNull(added);
    }

    @Test
    void editOrderTest() throws ParseException {
        //target testOrder
        Date testDate = newOrderDate;
        int testNumber = 1;
        newOrder.setOrderNumber(1);

        Order result = service.editOrder(testDate,testNumber,newOrder);
        Order got = service.getOrder(testDate,testNumber);

        Assertions.assertEquals(result,newOrder);
        Assertions.assertEquals(got,newOrder);
    }

    @Test
    void editOrderNotExistTest() throws ParseException {
        //target non-existent order
        Date testDate = newOrderDate;
        int testNumber = 3;

        Order result = service.editOrder(testDate,testNumber,newOrder);

        Assertions.assertNull(result);
    }

    @Test
    void removeOrderTest() throws ParseException {
        //target testOrder
        Date testDate = newOrderDate;
        int testNumber = 1;

        Order testOrder = service.getOrder(testDate,testNumber);

        Order result = service.removeOrder(testDate,testNumber);
        Order got = service.getOrder(testDate,testNumber);

        Assertions.assertEquals(result,testOrder);
        Assertions.assertNull(got);
    }

    @Test
    void removeOrderNotExistTest() throws ParseException {
        //target non-existent order
        Date testDate = newOrderDate;
        int testNumber = 3;

        Order result = service.removeOrder(testDate,testNumber);

        Assertions.assertNull(result);
    }

    @Test
    void getTaxesTest() throws ParseException {

        List<Tax> result = service.getTaxes();

        Assertions.assertEquals("TestState", result.get(0).getState());
    }

    @Test
    void getProductsTest() throws ParseException {


        List<Product> result = service.getProducts();

        Assertions.assertEquals("TestProduct", result.get(0).getProductType());
    }

}
