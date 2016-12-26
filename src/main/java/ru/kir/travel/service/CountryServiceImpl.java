package ru.kir.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kir.travel.dao.CountryDao;
import ru.kir.travel.model.Country;

/**
 * Created by Kirill Zhitelev on 25.12.2016.
 */
@Service("countryService")
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryDao countryDao;

    @Override
    @Transactional(readOnly = true)
    public Country getCountry(String country) {
        return countryDao.getCountry(country);
    }
}
