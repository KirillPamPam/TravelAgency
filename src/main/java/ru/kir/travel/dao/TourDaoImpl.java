package ru.kir.travel.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kir.travel.model.Parameters;
import ru.kir.travel.model.Tour;

import java.util.List;

/**
 * Created by Kirill Zhitelev on 25.12.2016.
 */
@Repository("tourDao")
public class TourDaoImpl implements TourDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Tour getTour(String country) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Tour t where t.country.countryName = :country");
        query.setParameter("country", country);

        return (Tour) query.uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tour> getParametersTours(Parameters params) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(getParamsQuery(params));
        setQueryParams(query, params);

        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tour> getAllTours() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(Tour.class).list();
    }

    private String getParamsQuery(Parameters params) {
        String getQuery = "from Tour t where ";
        if(!params.getCountry().equals("")) {
            getQuery += "t.country.countryName = :country";
        }
        if(!params.getResort().equals("")) {
            getQuery += " and t.resort.resortName = :resort";
        }
        if(params.getStars() != 0) {
            getQuery += " and t.hotel.stars = :stars";
        }

        getQuery += " and t.cost between :costFrom and :costTo";

        return getQuery;
    }

    private void setQueryParams(Query query, Parameters params) {
        if(!params.getCountry().equals("")) {
            query.setParameter("country", params.getCountry());
        }
        if(!params.getResort().equals("")) {
            query.setParameter("resort", params.getResort());
        }
        if(params.getStars() != 0) {
            query.setParameter("stars", params.getStars());
        }
        query.setParameter("costFrom", params.getCostFrom());
        query.setParameter("costTo", params.getCostTo());
    }
}
