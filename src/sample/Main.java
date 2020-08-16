package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Visi-sort: Sorting algorithm Visualizer
 * @author Brian Chalfant 2020
 * This program visualizes the sorting process for BubbleSort, InsertionSort and Quicksort algorithms
 *
 */


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Load FXML
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //Set Icon
        primaryStage.getIcons().add(new Image("sample/vs.png"));
        //Set Title
        primaryStage.setTitle("Visi-Sort");
        //Create new Scene and add Stylesheet
        Scene scene = new Scene(root, 640, 275); scene.getStylesheets().add("sample/stylesheet.css");
        primaryStage.setScene(scene);

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
