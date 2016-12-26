package ru.kir.travel.model;

/**
 * Created by Kirill Zhitelev on 25.12.2016.
 */
public class Client {
    private String fio;
    private String passport;
    private TableTour tableTour;

    public Client(String fio, String passport, TableTour tableTour) {
        this.fio = fio;
        this.passport = passport;
        this.tableTour = tableTour;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public TableTour getTableTour() {
        return tableTour;
    }

    public void setTableTour(TableTour tableTour) {
        this.tableTour = tableTour;
    }
}
