package ru.kir.travel.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import ru.kir.travel.ui.pages.MainPage;

/**
 * Created by Kirill Zhitelev on 25.12.2016.
 */
public class MainTravel extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationContext context = new GenericXmlApplicationContext("/spring/app-context-config.xml");

        MainPage mainPage = new MainPage(primaryStage, context);

        primaryStage.setScene(mainPage.getScene());

        primaryStage.setWidth(958);
        primaryStage.setHeight(650);
        primaryStage.setTitle("Travel Agency");
     //   primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
