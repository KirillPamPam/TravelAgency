package ru.kir.travel.model;

/**
 * Created by Kirill Zhitelev on 25.12.2016.
 */
public class Parameters {
    private String country;
    private String resort;
    private int stars;
    private int costFrom;
    private int costTo;

    public Parameters() {
    }

    public Parameters(String country, String resort, int stars, int costFrom, int costTo) {
        this.country = country;
        this.resort = resort;
        this.stars = stars;
        this.costFrom = costFrom;
        this.costTo = costTo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getResort() {
        return resort;
    }

    public void setResort(String resort) {
        this.resort = resort;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getCostFrom() {
        return costFrom;
    }

    public void setCostFrom(int costFrom) {
        this.costFrom = costFrom;
    }

    public int getCostTo() {
        return costTo;
    }

    public void setCostTo(int costTo) {
        this.costTo = costTo;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "country='" + country + '\'' +
                ", resort='" + resort + '\'' +
                ", stars=" + stars +
                ", costFrom=" + costFrom +
                ", costTo=" + costTo +
                '}';
    }
}
