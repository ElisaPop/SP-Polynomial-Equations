package main.unit;

import gui.Model;
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * This application allows the user to benefit from the variety of Polynomial equations that will be computed in less than a second
 * by its own computer.
 *
 * The maximum number of Polynomials is 2, and certain operations require only one Polynomial. The Polynomial that will be chosen
 * is always the first Polynomial.
 *
*/

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) {

        primaryStage.setScene(Model.mainInterface());
        Model.alertOnClose(primaryStage);
        primaryStage.setTitle("Polynomial equations");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
