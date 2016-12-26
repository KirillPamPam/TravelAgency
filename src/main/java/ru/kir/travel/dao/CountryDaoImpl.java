package ru.kir.travel.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kir.travel.model.Country;

/**
 * Created by Kirill Zhitelev on 25.12.2016.
 */
@Repository("countryDao")
public class CountryDaoImpl implements CountryDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Country getCountry(String country) {
        Session session = sessionFactory.getCurrentSession();
        return (Country) session
                .createCriteria(Country.class)
                .add(Restrictions.eq("countryName", country))
                .uniqueResult();
    }
}
