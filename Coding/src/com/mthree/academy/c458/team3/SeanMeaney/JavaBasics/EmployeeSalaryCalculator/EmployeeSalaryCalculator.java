package com.mthree.academy.c458.team3.SeanMeaney.JavaBasics.EmployeeSalaryCalculator;

public class EmployeeSalaryCalculator {
    public static void main(String[] args) {
        return;
    }


}

class Employee {
    String employeeID;
    String employeeName;
    int hoursWorked;
    double hourlyRate;

    public Employee(){}

    public Employee(String employeeID, String employeeName, int hoursWorked, double hourlyRate){
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public String formatPayslip() {
        return "================PAYSLIP==============\n" +
                "\n" +
                "Employee ID       :     "+employeeID+"\n" +
                "\n" +
                "Employee Name     :     "+employeeName+"\n" +
                "\n" +
                "Hours Worked      :     "+hoursWorked+"\n" +
                "\n" +
                "Hourly Rate       :     "+hourlyRate+"\n" +
                "\n" +
                "Overtime          :     " + "\n" +
                "\n" +
                "Overtime Rate     :     " + "\n" +
                "\n" +
                "Overtime Pay      :     " + "\n" +
                "\n" +
                "Basic Pay         :     " + "\n" +
                "\n" +
                "Gross Salary      :     ";
    }
}
