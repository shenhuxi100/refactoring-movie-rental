package cn.xpbootcamp.refactor.service;

import cn.xpbootcamp.refactor.Movie;

public class CalculateCampusRentalAmount implements CalculateRentalAmount {
    double initialAmountUnit = 1.5;
    int initialDays = 3;
    double deltaAmountUnit = 1.5;

    @Override
    public int getMovieType() {
        return Movie.CAMPUS;
    }

    @Override
    public double calculateRentalAmount(int daysRented, double rentalAmount) {
        rentalAmount += initialAmountUnit;
        if (daysRented > initialDays)
            rentalAmount += (daysRented - initialDays) * deltaAmountUnit;
        return rentalAmount;
    }
}
