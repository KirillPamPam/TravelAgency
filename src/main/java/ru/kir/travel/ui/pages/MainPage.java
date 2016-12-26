package ru.kir.travel.ui.pages;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import ru.kir.travel.model.Parameters;
import ru.kir.travel.model.Tour;
import ru.kir.travel.service.TourService;
import ru.kir.travel.ui.UIHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kirill Zhitelev on 25.12.2016.
 */
public class MainPage {
    private Scene scene;
    private Stage stage;
    private TextField countryField = new TextField();
    private TextField resortField = new TextField();
    private TextField starsField = new TextField();
    private TextField costFieldFrom = new TextField();
    private TextField costFieldTo = new TextField();
    private Button search = new Button("Поиск");
    private ApplicationContext context;

    public MainPage(Stage stage, ApplicationContext context) {
        this.stage = stage;
        this.context = context;

        initPage();
    }

    private void initPage() {
        BorderPane pane = new BorderPane();

        Label agency = new Label("Travel Agency");
        Label countryLabel = new Label("Страна");
        Label resortLabel = new Label("Курорт");
        Label starsLabel = new Label("Количество звезд отеля");
        Label costLabel = new Label("Стоимость от");
        Label costTo = new Label("до");
        Label tours = new Label("Поиск туров");
        agency.setFont(new Font(60));
        tours.setFont(new Font(20));

        HBox countries = new HBox(20);
        countries.setAlignment(Pos.CENTER);
        countries.getChildren().addAll(countryLabel, countryField);

        HBox resorts = new HBox(20);
        resorts.setAlignment(Pos.CENTER);
        resorts.getChildren().addAll(resortLabel, resortField);

        HBox stars = new HBox(20);
        stars.setAlignment(Pos.CENTER);
        starsField.setMaxWidth(100);
        stars.getChildren().addAll(starsLabel, starsField);

        HBox costs = new HBox(15);
        costs.setAlignment(Pos.CENTER);
        costFieldFrom.setMaxWidth(80);
        costFieldTo.setMaxWidth(80);
        costs.getChildren().addAll(costLabel, costFieldFrom, costTo, costFieldTo);

        VBox mainBox = new VBox(30);
        mainBox.setAlignment(Pos.CENTER);


        mainBox.getChildren().addAll(agency, tours, countries, resorts, stars, costs, search);

        pane.setCenter(mainBox);

        eventHandle();

        scene = new Scene(pane);
    }

    public Scene getScene() {
        return scene;
    }

    private void eventHandle() {
        TourService tourService = context.getBean("tourService", TourService.class);
        search.setOnAction(event -> {
            if(countryField.getText().equals("")) {
                UIHelper.makeInformationWindow(Alert.AlertType.ERROR, "Введите, пожалуйста, страну и нужные параметры", null, null);

            }
            else {
                String countryParam = countryField.getText();
                String resortParam = resortField.getText();
                int starsParam = starsField.getText().equals("") ? 0 : Integer.parseInt(starsField.getText());
                int costFrom = costFieldFrom.getText().equals("") ? 0 : Integer.parseInt(costFieldFrom.getText());
                int costTo = costFieldTo.getText().equals("") ? 500000 : Integer.parseInt(costFieldTo.getText());

                Parameters parameters = new Parameters(countryParam, resortParam, starsParam, costFrom, costTo);
                System.out.println(parameters);
                List<Tour> tours = tourService.getParametersTours(parameters);

                Map<String, String> stars = new HashMap<>();
                tours.forEach(tour -> stars.put(tour.getHotel().getHotelName(), tour.getHotel().getDesc()));

                TourPage tourPage = new TourPage(stage, this);
                tourPage.initData(tours, stars);


                stage.setScene(tourPage.getScene());
            }
        });
    }
}
