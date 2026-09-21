package org.WileyEdgeCorp.FlooringMastery.service;

import org.WileyEdgeCorp.FlooringMastery.dao.*;
import org.WileyEdgeCorp.FlooringMastery.dao.stubs.OrderDaoStub;
import org.WileyEdgeCorp.FlooringMastery.dao.stubs.ProductDaoStub;
import org.WileyEdgeCorp.FlooringMastery.dao.stubs.TaxDaoStub;
import org.WileyEdgeCorp.FlooringMastery.dto.Order;
import org.WileyEdgeCorp.FlooringMastery.dto.Product;
import org.WileyEdgeCorp.FlooringMastery.dto.Tax;
import org.WileyEdgeCorp.FlooringMastery.exceptions.DataCollisionException;
import org.WileyEdgeCorp.FlooringMastery.exceptions.NoDataLoaded;
import org.WileyEdgeCorp.FlooringMastery.exceptions.NoSuchOrderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
        //service = ctx.getBean("serviceLayer", ServiceLayer.class);
        service = new ServiceLayerImpl(new OrderDaoStub(),new ProductDaoStub(), new TaxDaoStub());

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

        Order result = service.getOrder(testNumber);

        Assertions.assertNotNull(result);
    }

    @Test
    void getOrderNotExistTest() throws ParseException {
        //target non-existent order
        Date testDate = newOrderDate;
        int testNumber = 3;

        Order result = service.getOrder(testNumber);

        Assertions.assertNull(result);
    }

    @Test
    void getAddOrder() throws ParseException, DataCollisionException {
        //target newOrder
        Date testDate = newOrderDate;
        int testNumber = newOrderNumber;

        Order added = service.addOrder(newOrder);

        Assertions.assertEquals(newOrder, added);
    }

    @Test
    void getAddOrderCollision() throws ParseException, DataCollisionException {
        //target testOrder
        newOrder.setOrderNumber(1);

        try {
            Order added = service.addOrder(newOrder);
            Assertions.fail();
        } catch (DataCollisionException e) {
        }
    }

    @Test
    void editOrderTest() throws ParseException {
        //target testOrder
        Date testDate = newOrderDate;
        int testNumber = 1;
        newOrder.setOrderNumber(1);

        Order result = service.editOrder(newOrder);
        Order got = service.getOrder(testNumber);

        Assertions.assertEquals(result,newOrder);
        Assertions.assertEquals(got,newOrder);
    }

    @Test
    void editOrderNotExistTest() throws ParseException {
        //target non-existent order
        Date testDate = newOrderDate;
        int testNumber = 3;

        Order result = null;
        try {
            service.editOrder(newOrder);
            Assertions.fail();
        } catch (NoSuchOrderException e) {
        }
    }

    @Test
    void removeOrderTest() throws ParseException {
        //target testOrder
        Date testDate = newOrderDate;
        int testNumber = 1;

        Order testOrder = service.getOrder(testNumber);

        Order result = service.removeOrder(testNumber);
        Order got = service.getOrder(testNumber);

        Assertions.assertEquals(result,testOrder);
        Assertions.assertNull(got);
    }

    @Test
    void removeOrderNotExistTest() throws ParseException {
        //target non-existent order
        Date testDate = newOrderDate;
        int testNumber = 3;

        Order result = service.removeOrder(testNumber);

        Assertions.assertNull(result);
    }

    @Test
    void getTaxesTest() throws ParseException, NoDataLoaded {

        List<Tax> result = service.getTaxes();

        Assertions.assertEquals("TestState", result.get(0).getState());
    }

    @Test
    void getProductsTest() throws ParseException, NoDataLoaded {


        List<Product> result = service.getProducts();

        Assertions.assertEquals("TestProduct", result.get(0).getProductType());
    }

}
