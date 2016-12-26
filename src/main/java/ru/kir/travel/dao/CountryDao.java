package ru.kir.travel.dao;

import ru.kir.travel.model.Country;

/**
 * Created by Kirill Zhitelev on 25.12.2016.
 */
public interface CountryDao {
    Country getCountry(String country);
}
