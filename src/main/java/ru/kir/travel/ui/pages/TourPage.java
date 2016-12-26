package ru.kir.travel.ui.pages;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.modelmapper.ModelMapper;
import ru.kir.travel.model.Client;
import ru.kir.travel.model.TableTour;
import ru.kir.travel.model.Tour;
import ru.kir.travel.ui.UIHelper;
import ru.kir.travel.ui.VerticalValuesReport;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Kirill Zhitelev on 25.12.2016.
 */
public class TourPage {
    private Scene scene;
    private Stage stage;
    private ObservableList<TableTour> toursData = FXCollections.observableArrayList();
    private TableView<TableTour> tourTable = new TableView<>();
    private TableColumn<TableTour, Date> date = new TableColumn<>("Дата заезда");
    private TableColumn<TableTour, Integer> nights = new TableColumn<>("Ночей");
    private TableColumn<TableTour, String> hotel = new TableColumn<>("Отель");
    private TableColumn<TableTour, String> country = new TableColumn<>("Страна");
    private TableColumn<TableTour, String> resort = new TableColumn<>("Курорт");
    private TableColumn<TableTour, String> food = new TableColumn<>("Тип питания");
    private TableColumn<TableTour, Integer> cost= new TableColumn<>("Стоимость, $");
    private TableColumn<TableTour, Integer> placement = new TableColumn<>("Размещение");
    private Map<String, String> hotels;
    private MainPage mainPage;
    private Button search = new Button("К поиску");
    private Button issue = new Button("Оформить");
    private TextField fio = new TextField();
    private TextField passport = new TextField();

    public TourPage(Stage stage, MainPage mainPage) {
        this.stage = stage;
        this.mainPage = mainPage;
        iniPage();
    }

    private void iniPage() {
        scene = new Scene(new Group());
        VBox vBox = new VBox(10);

        country.setPrefWidth(150);
        resort.setPrefWidth(150);
        hotel.setPrefWidth(150);
        date.setPrefWidth(100);
        nights.setPrefWidth(50);
        food.setPrefWidth(150);
        placement.setPrefWidth(100);
        cost.setPrefWidth(100);

        tourTable.setPrefHeight(400);

        tourTable.getColumns().addAll(country, resort, hotel, date, nights, food, placement, cost);

        tourTable.getSelectionModel().setCellSelectionEnabled(true);

        HBox hBox = new HBox(20);
        hBox.getChildren().addAll(search, issue);

        VBox fioBox = new VBox(10);
        fio.setMaxWidth(400);
        fioBox.getChildren().addAll(new Label("ФИО"), fio);

        VBox passportBox = new VBox(10);
        passport.setMaxWidth(100);
        passportBox.getChildren().addAll(new Label("Пасспорт"), passport);

        HBox data = new HBox(20);
        data.getChildren().addAll(fioBox, passportBox);

        vBox.getChildren().addAll(tourTable, data, hBox);

        ((Group) scene.getRoot()).getChildren().addAll(vBox);

        initParams();

        eventHandle();

        tourTable.setItems(toursData);

    }

    public void initData(List<Tour> tours, Map<String, String> stars) {
        tours.forEach(tour -> {
            ModelMapper mapper = new ModelMapper();
            TableTour tableTour = mapper.map(tour, TableTour.class);
            toursData.add(tableTour);
        });
        this.hotels = stars;
    }

    private void initParams() {
        date.setCellValueFactory(new PropertyValueFactory<TableTour, Date>("arrivalDate"));
        nights.setCellValueFactory(new PropertyValueFactory<TableTour, Integer>("nights"));
        hotel.setCellValueFactory(new PropertyValueFactory<TableTour, String>("hotel"));
        country.setCellValueFactory(new PropertyValueFactory<TableTour, String>("country"));
        resort.setCellValueFactory(new PropertyValueFactory<TableTour, String>("resort"));
        food.setCellValueFactory(new PropertyValueFactory<TableTour, String>("food"));
        placement.setCellValueFactory(new PropertyValueFactory<TableTour, Integer>("placement"));
        cost.setCellValueFactory(new PropertyValueFactory<TableTour, Integer>("cost"));
    }

    private void eventHandle() {
        tourTable.setOnMouseClicked(event -> {
            int row = tourTable.getFocusModel().getFocusedCell().getRow();
            int column = tourTable.getFocusModel().getFocusedCell().getColumn();
            if (column == 2) {
                String hotel = (String) tourTable.getFocusModel()
                        .getFocusedCell()
                        .getTableColumn()
                        .getCellObservableValue(row)
                        .getValue();
                if (hotels.containsKey(hotel)) {
                    UIHelper.makeInformationWindow(Alert.AlertType.INFORMATION, hotels.get(hotel), null, hotel);
                }
            }
        });

        issue.setOnAction(event -> {
            if(fio.getText().equals("") || passport.getText().equals("")) {
                UIHelper.makeInformationWindow(Alert.AlertType.ERROR, "Введите ФИО и паспорт", null, null);
                return;
            }
            TableTour tableTour = tourTable.getFocusModel().getFocusedItem();
            Client client = new Client(fio.getText(), passport.getText(), tableTour);
            new VerticalValuesReport(client);
        });

        search.setOnAction(event -> stage.setScene(mainPage.getScene()));
    }

    public Scene getScene() {
        return scene;
    }
}
