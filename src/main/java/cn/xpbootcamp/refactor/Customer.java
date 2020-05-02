package cn.xpbootcamp.refactor;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {

    private String customerName;
    private Vector<Rental> rentals = new Vector<>();
    private double totalAmount;
    private int frequentRenterPoints;

    Customer(String customerName) {
        this.customerName = customerName;
    }

    void addRental(Rental rental) {
        rentals.addElement(rental);
    }

    String statement() {
        StringBuilder statementResult = buildHeader();
        buildBody(this, statementResult);
        buildFooter(totalAmount, frequentRenterPoints, statementResult);

        return statementResult.toString();
    }

    private static void buildBody(Customer customer, StringBuilder statementResult) {
        Enumeration<Rental> rentals = customer.rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental rental = rentals.nextElement();
            rental.buildRentalAmount(statementResult);

            customer.frequentRenterPoints = rental.addFrequentRenterPoint(customer.frequentRenterPoints);
            customer.totalAmount += rental.getRentalAmount();
        }
    }

    private StringBuilder buildHeader() {
        return new StringBuilder("Rental Record for " + this.customerName + "：\n");
    }

    private void buildFooter(double totalAmount, int frequentRenterPoints, StringBuilder statementResult) {
        statementResult.append("Amount owed is ").append(totalAmount).append("\n");
        statementResult.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
    }
}
