package cn.xpbootcamp.refactor.service;

import cn.xpbootcamp.refactor.Movie;

public class CalculateHistoryRentalAmount implements CalculateRentalAmount {
    double initialAmountUnit = 2;
    int initialDays = 2;
    double deltaAmountUnit = 1.5;

    @Override
    public int getMovieType() {
        return Movie.HISTORY;
    }

    @Override
    public double calculateRentalAmount(int daysRented, double rentalAmount) {
        rentalAmount += initialAmountUnit;
        if (daysRented > initialDays)
            rentalAmount += (daysRented - initialDays) * deltaAmountUnit;
        return rentalAmount;
    }
}
