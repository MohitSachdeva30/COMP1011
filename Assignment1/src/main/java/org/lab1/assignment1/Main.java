package org.lab1.assignment1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {

    private TableView<Game> tableView;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Video Game Data");

        // Chart setup
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Top Selling Video Games");
        xAxis.setLabel("Games");
        yAxis.setLabel("Sales (Million)");

        // TableView setup
        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Game, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Game, String> genreCol = new TableColumn<>("Genre");
        genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        TableColumn<Game, String> platformCol = new TableColumn<>("Platform");
        platformCol.setCellValueFactory(new PropertyValueFactory<>("platform"));
        TableColumn<Game, Integer> releaseYearCol = new TableColumn<>("Release Year");
        releaseYearCol.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
        TableColumn<Game, Double> salesMillionCol = new TableColumn<>("Sales (Million)");
        salesMillionCol.setCellValueFactory(new PropertyValueFactory<>("salesMillion"));

        tableView.getColumns().addAll(titleCol, genreCol, platformCol, releaseYearCol, salesMillionCol);

        // Button to switch between Chart and TableView
        Button switchButton = new Button("Switch View");
        switchButton.setOnAction(e -> {
            if (primaryStage.getScene().getRoot() instanceof BorderPane) {
                BorderPane root = (BorderPane) primaryStage.getScene().getRoot();
                if (root.getCenter() == barChart) {
                    root.setCenter(tableView);
                    switchButton.setText("Switch to Chart");
                } else {
                    root.setCenter(barChart);
                    switchButton.setText("Switch to TableView");
                }
            }
        });

        // Layout setup
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(switchButton);
        BorderPane.setMargin(switchButton, new Insets(10));

        mainPane.setCenter(barChart);

        Scene scene = new Scene(mainPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Populate data initially
        populateChart(barChart);
        populateTableView();
    }

    // Method to populate BarChart with data from database
    private void populateChart(BarChart<String, Number> barChart) {
        try {
            ResultSet rs = DBUtility.getAllGames();
            ObservableList<XYChart.Series<String, Number>> seriesList = FXCollections.observableArrayList();

            while (rs.next()) {
                String title = rs.getString("title");
                double sales = rs.getDouble("sales_million");

                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName(title);
                series.getData().add(new XYChart.Data<>("Sales", sales));
                seriesList.add(series);
            }

            barChart.setData(seriesList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to populate TableView with data from database
    private void populateTableView() {
        try {
            ResultSet rs = DBUtility.getAllGames();
            ObservableList<Game> gamesList = FXCollections.observableArrayList();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                String platform = rs.getString("platform");
                int releaseYear = rs.getInt("release_year");
                double salesMillion = rs.getDouble("sales_million");

                Game game = new Game(id, title, genre, platform, releaseYear, salesMillion);
                gamesList.add(game);
            }

            tableView.setItems(gamesList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
