package ru.kir.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kir.travel.dao.TourDao;
import ru.kir.travel.model.Parameters;
import ru.kir.travel.model.Tour;

import java.util.List;

/**
 * Created by Kirill Zhitelev on 25.12.2016.
 */
@Service("tourService")
public class TourServiceImpl implements TourService {
    @Autowired
    private TourDao tourDao;

    @Override
    @Transactional
    public Tour getTour(String country) {
        return tourDao.getTour(country);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tour> getParametersTours(Parameters params) {
        return tourDao.getParametersTours(params);
    }

    @Override
    public List<Tour> getAllTours() {
        return tourDao.getAllTours();
    }
}
