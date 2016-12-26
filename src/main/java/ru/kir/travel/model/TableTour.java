package ru.kir.travel.model;

import java.sql.Date;

/**
 * Created by Kirill Zhitelev on 25.12.2016.
 */
public class TableTour {
    private Date arrivalDate;
    private String country;
    private int nights;
    private String food;
    private String hotel;
    private String resort;
    private int placement;
    private int cost;

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getResort() {
        return resort;
    }

    public void setResort(String resort) {
        this.resort = resort;
    }

    public int getPlacement() {
        return placement;
    }

    public void setPlacement(int placement) {
        this.placement = placement;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "TableTour{" +
                "arrivalDate=" + arrivalDate +
                ", country='" + country + '\'' +
                ", nights=" + nights +
                ", food='" + food + '\'' +
                ", hotel='" + hotel + '\'' +
                ", resort='" + resort + '\'' +
                ", placement=" + placement +
                ", cost=" + cost +
                '}';
    }
}
