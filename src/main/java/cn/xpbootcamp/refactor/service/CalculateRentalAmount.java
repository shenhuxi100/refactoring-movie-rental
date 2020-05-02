package cn.xpbootcamp.refactor.service;

public interface CalculateRentalAmount {
    int getMovieType();
    double calculateRentalAmount(int daysRented, double rentalAmount);
}
