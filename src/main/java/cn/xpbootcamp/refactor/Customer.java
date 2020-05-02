package cn.xpbootcamp.refactor;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {

    private String customerName;
    private Vector<Rental> rentals = new Vector<>();

    Customer(String customerName) {
        this.customerName = customerName;
    }

    void addRental(Rental rental) {
        rentals.addElement(rental);
    }

    public String getCustomerName() {
        return customerName;
    }

    String statement() {
        double totalAmount = 0d;
        int frequentRenterPoints = 0;
        Enumeration<Rental> rentals = this.rentals.elements();
        StringBuilder statementResult = new StringBuilder("Rental Record for " + getCustomerName() + "：\n");
        while (rentals.hasMoreElements()) {
            Rental each = rentals.nextElement();
            //show figures for this rental
            //determine amounts for each line
            double thisAmount = each.getRentalAmount();
            switch (each.getMovie().getMovieType()) {
                case Movie.HISTORY:
                    thisAmount += 2;
                    if (each.getDaysRented() > 2)
                        thisAmount += (each.getDaysRented() - 2) * 1.5;
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.getDaysRented() * 3;
                    break;
                case Movie.CAMPUS:
                    thisAmount += 1.5;
                    if (each.getDaysRented() > 3)
                        thisAmount += (each.getDaysRented() - 3) * 1.5;
                    break;
            }
            //add frequent renter points
            frequentRenterPoints++;
            if ((each.getMovie().getMovieType() == Movie.NEW_RELEASE) && each.getDaysRented() > 1)
                frequentRenterPoints++;

            //show figures for this rental
            statementResult.append("\t")
                  .append(each.getMovie().getMovieTitle())
                  .append("\t")
                  .append(thisAmount).append("\n");
            totalAmount += thisAmount;
        }
        //add footer lines
        statementResult.append("Amount owed is ").append(totalAmount).append("\n");
        statementResult.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return statementResult.toString();
    }

}
