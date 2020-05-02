package cn.xpbootcamp.refactor.service;

import cn.xpbootcamp.refactor.Movie;

public class CalculateNewReleaseRentalAmount implements CalculateRentalAmount {
    double deltaAmountUnit = 3;

    @Override
    public int getMovieType() {
        return Movie.NEW_RELEASE;
    }

    @Override
    public double calculateRentalAmount(int daysRented, double rentalAmount) {
        rentalAmount += daysRented * deltaAmountUnit;
        return rentalAmount;
    }
}
