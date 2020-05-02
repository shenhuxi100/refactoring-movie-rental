package cn.xpbootcamp.refactor;

import cn.xpbootcamp.refactor.service.CalculateRentalAmount;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class Rental {

    Movie movie;
    private int daysRented;
    private double rentalAmount;
    private static Map<Integer, CalculateRentalAmount> ioc = new HashMap<Integer, CalculateRentalAmount>();

    static {
        ServiceLoader<CalculateRentalAmount> serviceLoader = ServiceLoader.load(CalculateRentalAmount.class);
        serviceLoader.forEach(service -> ioc.put(service.getMovieType(), service));
    }

    Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    double getRentalAmount() {
        return rentalAmount;
    }

    void buildRentalAmount(StringBuilder statementResult) {
        calculateRentalAmount();
        addRentalFigure(statementResult);
    }

    int addFrequentRenterPoint(int frequentRenterPoints) {
        frequentRenterPoints++;
        if ((movie.getMovieType() == Movie.NEW_RELEASE) && daysRented > 1)
            frequentRenterPoints++;
        return frequentRenterPoints;
    }

    private void calculateRentalAmount() {
        rentalAmount = ioc.get(movie.getMovieType()).calculateRentalAmount(daysRented, rentalAmount);
    }

    private void addRentalFigure(StringBuilder statementResult) {
        statementResult.append("\t")
                .append(movie.getMovieTitle())
                .append("\t")
                .append(rentalAmount).append("\n");
    }
}
