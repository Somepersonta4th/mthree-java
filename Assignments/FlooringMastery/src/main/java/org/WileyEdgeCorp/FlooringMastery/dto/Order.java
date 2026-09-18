package org.WileyEdgeCorp.FlooringMastery.dto;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private int orderNumber;
    private String customerName;
    private String state;
    private Date orderDate;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal costPerSquareFoot;
    private BigDecimal labourCostPerSquareFoot;
    private BigDecimal area;
    private BigDecimal materialCost;
    private BigDecimal labourCost;
    private BigDecimal tax;
    private BigDecimal total;

    //for percent conversion
    private final BigDecimal PERCENT = new BigDecimal("0.01");

    public Order() {}

    //getters + setters*
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
        this.updateCosts();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
        this.updateCosts();
    }

    public BigDecimal getLabourCostPerSquareFoot() {
        return labourCostPerSquareFoot;
    }

    public void setLabourCostPerSquareFoot(BigDecimal labourCostPerSquareFoot) {
        this.labourCostPerSquareFoot = labourCostPerSquareFoot;
        this.updateCosts();
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
        this.updateCosts();
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public BigDecimal getLabourCost() {
        return labourCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public BigDecimal getTotal() {
        return total;
    }
    //*getters + setters

    private void updateCosts() {

        //dont calculate if no values
        if (area == null
        || costPerSquareFoot == null
        || labourCostPerSquareFoot == null) {
            return;
        }

        //calculate costs
        this.materialCost = area.multiply(costPerSquareFoot);
        this.labourCost = area.multiply(labourCostPerSquareFoot);

        //dont calculate if no values
        if (taxRate == null) {
            return;
        }

        //calculate tax
        this.tax = materialCost
                .add(labourCost)
                .multiply(taxRate.multiply(PERCENT));

        //calculate total
        this.total = materialCost
                .add(labourCost)
                .add(tax);
    }
}
