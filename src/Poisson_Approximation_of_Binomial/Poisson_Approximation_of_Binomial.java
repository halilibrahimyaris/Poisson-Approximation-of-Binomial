/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Poisson_Approximation_of_Binomial;

import static java.lang.Math.pow;
import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author halil
 */
public class Poisson_Approximation_of_Binomial extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) {

        window = primaryStage;
        window.setTitle("Poisson Approximation of Binomial");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label n = new Label("n");
        GridPane.setConstraints(n, 0, 0);

        TextField nInput = new TextField();
        GridPane.setConstraints(nInput, 0, 1);

        Label p = new Label("p");
        GridPane.setConstraints(p, 1, 0);

        TextField pInput = new TextField();
        GridPane.setConstraints(pInput, 1, 1);

        Label x = new Label("x");
        GridPane.setConstraints(x, 0, 2);

        TextField xInput = new TextField();
        GridPane.setConstraints(xInput, 0, 3);

        Label calc = new Label("X");
        GridPane.setConstraints(calc, 2, 2);

        TextField calcu = new TextField();
        GridPane.setConstraints(calcu, 2, 3);

        Label ex = new Label("μ=E(X)=");
        GridPane.setConstraints(ex, 3, 2);

        TextField exText = new TextField();
        GridPane.setConstraints(exText, 3, 3);

        Label std = new Label("σ=SD(X)=");
        GridPane.setConstraints(std, 2, 0);

        TextField stdTxt = new TextField();
        GridPane.setConstraints(stdTxt, 2, 1);

        Label Var = new Label("σ2=Var(X)=");
        GridPane.setConstraints(Var, 3, 0);

        TextField varTxt = new TextField();
        GridPane.setConstraints(varTxt, 3, 1);

        Button loginButton = new Button("calculate");
        GridPane.setConstraints(loginButton, 1, 5);
        String week_days[]
                = {"X=x", "X<x", "X>x"};

        // Create a combo box 
        
        ComboBox combo_box = new ComboBox(FXCollections.observableArrayList(week_days));
        GridPane.setConstraints(combo_box, 1, 3);
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("x");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("P(X=x)");

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                float n = Float.parseFloat(nInput.getText().toString());
                System.out.println(n);
                float p = Float.parseFloat(pInput.getText().toString());
                System.out.println(p);
                int x = Integer.parseInt(xInput.getText().toString());
                System.out.println(x);
                if ((n >= 30) && (p <= 0.05)) {
                    LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
                    XYChart.Series<Number, Number> data = new XYChart.Series<>();
                    for (int i = 0; i < 10; i++) {

                        data.getData().add(new XYChart.Data<Number, Number>(i, calc(i, n, p)));

                    }

                    EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent e) {
                            if (combo_box.getValue() == "X=x") {
                                calcu.setText(String.valueOf(calc(x, n, p)));
                            }
                            if (combo_box.getValue() == "X<x") {
                                float yeni = 0;
                                for (int i = 0; i < x; i++) {
                                    yeni += (calc(x, n, p));
                                }
                                calcu.setText(String.valueOf(yeni));

                            }
                            if (combo_box.getValue() == "X>x") {
                                float yeni = 0;
                                for (int i = x; i < 10; i++) {
                                    yeni += (calc(i, n, p));
                                }
                                calcu.setText(String.valueOf(yeni));

                            }

                        }
                    };

                    stdTxt.setText(String.valueOf(Math.pow((n * p * (1 - p)), 0.5)));
                    varTxt.setText((String.valueOf(n * p * (1 - p))));
                    exText.setText(String.valueOf(n * p));
                    combo_box.setOnAction(event2);
                    data.setName("Poisson Approximation of Binomial");
                    grid.getChildren().add(lineChart);
                    lineChart.getData().add(data);
                    GridPane.setConstraints(lineChart, 2, 5);
                } else if (!((n >= 30) && (p <= 0.05))) {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("((n>=30)&&(p<=0.05)) that condition not provide!!");
                    alert.setContentText("Are you ok with this?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
                        XYChart.Series<Number, Number> data = new XYChart.Series<>();
                        for (int i = 0; i < 10; i++) {

                            data.getData().add(new XYChart.Data<Number, Number>(i, calc(i, n, p)));
                        }

                        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent e) {
                                if (combo_box.getValue() == "X=x") {
                                    calcu.setText(String.valueOf(calc(x, n, p)));
                                }
                                if (combo_box.getValue() == "X<x") {
                                    float yeni = 0;
                                    for (int i = 0; i <= x; i++) {
                                        yeni += (calc(i, n, p));
                                    }
                                    calcu.setText(String.valueOf(yeni));

                                }
                                if (combo_box.getValue() == "X>x") {
                                    float yeni = 0;
                                    for (int i = x; i < 10; i++) {
                                        yeni += (calc(i, n, p));
                                    }
                                    calcu.setText(String.valueOf(yeni));

                                }

                            }
                        };

                        stdTxt.setText(String.valueOf(Math.pow((n * p * (1 - p)), 0.5)));
                        varTxt.setText((String.valueOf(n * p * (1 - p))));
                        combo_box.setOnAction(event2);
                        grid.getChildren().add(lineChart);
                        data.setName("Poisson Approximation of Binomial Graph");
                        lineChart.getData().add(data);
                        GridPane.setConstraints(lineChart, 2, 5);
                    } else {
                        alert.close();
                    }
                }

            }

        };

        loginButton.setOnAction(event);
        grid.getChildren().addAll(n, nInput, p, pInput, x, xInput, loginButton, calc, calcu, combo_box, std, stdTxt, varTxt, Var, ex, exText);

        Scene scene = new Scene(grid, 600, 400);
        window.setScene(scene);
        window.show();

    }

    public static long factorialUsingForLoop(int n) {
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }

    public static double calc(int i, float n, double p) {

        double L = n * p;
        double e = 2.71;
        double a = pow(e, -L);
        double b = pow(L, i);
        double current = a * b / factorialUsingForLoop(i);
        return current;

    }

    public static void main(String[] args) {
        launch(args);
    }

}
