package ru.kir.travel.service;

import ru.kir.travel.model.Parameters;
import ru.kir.travel.model.Tour;

import java.util.List;

/**
 * Created by Kirill Zhitelev on 25.12.2016.
 */
public interface TourService {
    Tour getTour(String country);
    List<Tour> getParametersTours(Parameters params);
    List<Tour> getAllTours();
}
