package ru.kir.travel.ui;

import javafx.scene.control.Alert;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import ru.kir.travel.model.TableTour;
import ru.kir.travel.model.Tour;

/**
 * Created by Kirill Zhitelev on 25.12.2016.
 */
public class UIHelper {
    public static void makeInformationWindow(Alert.AlertType type, String contentText, String headerText, String title) {
        Alert alert = new Alert(type);
        alert.setContentText(contentText);
        alert.setHeaderText(headerText);
        alert.setTitle(title);
        alert.showAndWait();
    }

    public static ModelMapper getTourMapper() {
        ModelMapper mapper = new ModelMapper();

        Converter<Tour, String> country = new AbstractConverter<Tour, String>() {
            @Override
            protected String convert(Tour tour) {
                return tour.getCountry().getCountryName();
            }
        };

        mapper.addMappings(new PropertyMap<Tour, TableTour>() {
            @Override
            protected void configure() {
                using(country).map(source).setCountry(null);
            }
        });

        return mapper;
    }
}
