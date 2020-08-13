package sample;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Controller {
    public Pane viewPane;
    final public int MAXSIZE = 100;
    int[] array = new int[]{68, 90, 69, 56, 57, 32, 38, 65, 77, 62, 50, 8, 35, 23, 83, 93, 93, 78, 67, 36, 30, 30, 88, 34, 6, 17, 9, 94, 87, 33, 15, 96, 94, 67, 33, 78, 19, 41, 50, 35, 75, 91, 68, 61, 26, 24, 40, 41, 88, 45, 28, 58, 45, 66, 70, 75, 19, 39, 93, 98, 49, 24, 39, 32, 79, 11, 35, 7, 28, 83, 50, 47, 12, 53, 12, 27, 35, 54, 6, 85, 96, 22, 52, 11, 71, 71, 0, 70, 39, 11, 94, 87, 30, 53, 33, 51, 13, 63, 69, 78,};


    public void initialize() {
    loadChart();
    }

    public void loadChart() {
        viewPane.getChildren().clear();
        NumberAxis xAxis = new NumberAxis(0, MAXSIZE, (MAXSIZE / 10));
        xAxis.setLabel("Value");
        NumberAxis yAxis = new NumberAxis(0, MAXSIZE, (MAXSIZE / 10));
        yAxis.setLabel("Index");
        ScatterChart<Number, Number> sc = new ScatterChart<>(xAxis, yAxis);
        sc.setTitle("Algorithm Graphing");
        XYChart.Series series = new XYChart.Series();
        series.setName("Testing");
        for (int i = 0; i < array.length; i++) {
            series.getData().add(new XYChart.Data<>(i, array[i]));
        }
        sc.getData().add(series);
        sc.maxHeight(200);
        sc.maxWidth(400);
        viewPane.getChildren().add(sc);

    }
    public void bubbleSort(){
        bubbleSort(array);
    }

    public int[] bubbleSort(int[] workingCopy) {
        PauseTransition pausetransition = new PauseTransition(Duration.seconds(1));
        int size = workingCopy.length;
        for(int i = 0;i < size-1;i++){
            //outer loop
            for(int j = 0; j < size-i-1; j++){
                //inner loop
                //compare leftmost variable against its right neighbor
                if(workingCopy[j] > workingCopy[j+1]){
                    //left is smaller than right so swap them
                    int temp = workingCopy[j];
                    workingCopy[j] = workingCopy[j+1];
                    workingCopy[j+1] = temp;
                    //swapped
                    loadChart();
                }
                // left is bigger than right so do nothing
                //inner loop
            }
            //outer loop
        }
        return workingCopy;
    }

}

