package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This method is part of the view. It contains mostly pop-ups for when significant events happen.
 */

public class UtilsGUI {

    private static boolean answer;

    /**
     * This alert box is used when the application is closed.
     * It contains two answers: yes or no. This answer is returned to be used by other methods.
     *
     * @param title Title of the window
     * @param message Message displayed
     * @return Returns the answer to the Message displayed
     */
    public static boolean exitAlert(String title, String message) {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL); // Block user interaction with other windows
        window.setTitle(title);
        window.setWidth(450);
        window.setHeight(200);

        Label label = new Label();
        label.setText(message);

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        Pane spacingPane = new Pane();

        VBox layout = new VBox(20);
        HBox options = new HBox(30);
        options.setAlignment(Pos.CENTER);
        options.getChildren().addAll(yesButton, noButton);
        layout.getChildren().addAll(label, options);
        layout.setAlignment(Pos.CENTER);

        BorderPane alertPane = new BorderPane();
        alertPane.setCenter(layout);

        alertPane.setStyle("-fx-base: rgb(80,91,107);" +
                "    -fx-background: rgb(44,51,61);");

        Scene scene = new Scene(alertPane);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

    /**
     * This method is a simple alert box for when unusual things happen. This includes certain errors. It notifies the user about them.
     * @param title Title of the window
     * @param message Message to be displayed
     * @param buttonMessage Text that the button will have.
     */

    public static void alert(String title, String message, String buttonMessage) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL); // Block user interaction with other windows
        window.setTitle(title);
        window.setWidth(450);
        window.setHeight(200);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button(buttonMessage);
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        layout.setStyle("-fx-base: rgb(80,91,107);" +
                "    -fx-background: rgb(44,51,61);");

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait(); //Display window and wait for it to be closed before returning
    }

}

