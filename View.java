package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

/**
 * This Class simply contains the whole interface.
 */

class View {

    static BorderPane mainInterfaceUI() {
        GridPane newGrid = new GridPane();

        newGrid.setHgap(10); // HBox and VBox used mainly for arranging the UI elements
        newGrid.setVgap(10);
        newGrid.setPadding(new Insets(10, 10, 10, 10));
        // the center part of the GridPane
        TextField polynomialText1 = new TextField();
        Label polynomialLabel1 = new Label("Polynomial 1: ");
        TextField polynomialText2 = new TextField();
        Label polynomialLabel2 = new Label("Polynomial 2: ");
        TextField polynomialText3 = new TextField();
        Label polynomialLabel3 = new Label("Result: ");
        //polynomialText3.setDisable(true); // I gave up on this because if the resulting polynomial was too long, we couldn't see it all.

        polynomialText2.setMaxWidth(300);
        polynomialText1.setMaxWidth(300);

        Region gridRegionH = new Region(); // Used for arranging: setting distance from the window's borders
        gridRegionH.setPrefHeight(200);
        VBox.setVgrow(gridRegionH, Priority.ALWAYS);

        Region gridRegionW = new Region();
        gridRegionW.setPrefWidth(40);
        VBox.setVgrow(gridRegionW, Priority.ALWAYS);

        Button clearBtn = new Button("Clear"); // Button that simply clears all the TextFields
        clearBtn.setOnAction(e -> {
            polynomialText1.clear();
            polynomialText2.clear();
            polynomialText3.clear();

        });

        //Adding and arranging the CENTER elements of BorderPane
        newGrid.add(gridRegionH, 0, 0);
        newGrid.add(polynomialLabel1, 1, 1);
        newGrid.add(polynomialText1, 2, 1);
        newGrid.add(polynomialLabel2, 1, 2);
        newGrid.add(polynomialText2, 2, 2);
        newGrid.add(polynomialLabel3, 1, 3);
        newGrid.add(polynomialText3, 2, 3);
        newGrid.add(clearBtn, 2, 4);
        newGrid.add(gridRegionW, 0, 1);

        // New region: the LEFT region
        Region hBoxRegion = new Region();
        hBoxRegion.setPrefWidth(200);
        HBox.setHgrow(hBoxRegion, Priority.ALWAYS);

        Region wBoxRegion = new Region();
        wBoxRegion.setPrefWidth(200);
        HBox.setHgrow(wBoxRegion, Priority.ALWAYS);

        VBox leftMenu = new VBox(10);
        Button btnAdd = new Button("Add");
        Button btnSubtract = new Button("Subtract");
        Button btnMultiply = new Button("Multiply");
        Button btnDivide = new Button("Divide");

        btnAdd.setPrefWidth(110);
        btnSubtract.setPrefWidth(110);
        btnMultiply.setPrefWidth(110);
        btnDivide.setPrefWidth(110);

        // the following lambda expressions prepares the input for the specific operation they cover, and returns the result as a String
        btnAdd.setOnAction(e -> {
            String pol1 = polynomialText1.getText();
            String pol2 = polynomialText2.getText();

            polynomialText3.setText(Model.viewAdd(pol1,pol2));
        });

        btnSubtract.setOnAction(e -> {
            String pol1 = polynomialText1.getText();
            String pol2 = polynomialText2.getText();

            polynomialText3.setText(Model.viewSubtract(pol1,pol2));
        });
        btnMultiply.setOnAction(e -> {
            String pol1 = polynomialText1.getText();
            String pol2 = polynomialText2.getText();

            polynomialText3.setText(Model.viewMultiply(pol1,pol2));
        });
        btnDivide.setOnAction(e -> {
            String pol1 = polynomialText1.getText();
            String pol2 = polynomialText2.getText();

            polynomialText3.setText(Model.viewDivide(pol1,pol2));
        });

        leftMenu.getChildren().addAll(hBoxRegion, btnAdd, btnSubtract, btnMultiply, btnDivide);
        leftMenu.setAlignment(Pos.CENTER);

        // New Region of BorderPane : RIGHT
        VBox rightMenu = new VBox(10);
        Button btnDifferentiate = new Button("Differentiate");
        Button btnIntegrate = new Button("Integrate");
        Button btnEvaluate = new Button("Evaluate:");
        btnDifferentiate.setPrefWidth(110);
        btnIntegrate.setPrefWidth(110);
        btnEvaluate.setPrefWidth(110);

        btnDifferentiate.setOnAction(e -> {
            String pol1 = polynomialText1.getText();
            polynomialText3.setText(Model.viewDifferentiate(pol1));
        });
        btnIntegrate.setOnAction(e -> {
            String pol1 = polynomialText1.getText();
            polynomialText3.setText(Model.viewIntegrate(pol1));
        });
        TextField eval = new TextField();
        eval.setMaxWidth(110);
        btnEvaluate.setOnAction(e -> {
            String pol1 = polynomialText1.getText();
            String x = eval.getText();
            polynomialText3.setText(Model.viewEvaluation(pol1,x));
        });


        rightMenu.getChildren().addAll(wBoxRegion, btnDifferentiate, btnIntegrate, btnEvaluate, eval);
        rightMenu.setAlignment(Pos.CENTER);

        //Sets the final BorderPane
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(newGrid);
        borderPane.setLeft(leftMenu);
        borderPane.setRight(rightMenu);

        // CSS for giving the application some colour
        borderPane.setStyle("-fx-base: rgb(80,91,107);" +
                "    -fx-background: rgb(44,51,61);");

        return borderPane;
    }

}
