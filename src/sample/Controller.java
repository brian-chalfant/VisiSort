package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import java.util.Random;

public class Controller {
    public Button btnbubble;
    public Button btninsert;
    public Button btnquick;
    public Button btnreset;
    public Slider sliderSpeed;
    int[] array = new int[]{68, 90, 69, 56, 57, 32, 38, 65, 77, 62, 50, 8};
    SortNode[] snarray = new SortNode[array.length];
    public BetterPane viewPane;
    final public int MAXSIZE = 100;
    public int SPEED = 250;


    public void initialize() {
        for (int i = 0; i < array.length; i++) {
            snarray[i] = new SortNode(25, array[i]*2, array[i], (i * 26));
        }
        viewPane.setArrayfield(snarray);
        viewPane.getChildren().addAll(snarray);

        btnbubble.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                new Thread(() -> {
                    btninsert.setDisable(true);
                    btnbubble.setDisable(true);
                    btnquick.setDisable(true);
                    try {
                        for (int i = 0; i < snarray.length; i++) {
                            for (int j = 0; j < snarray.length - 1; j++) {
                                if (snarray[j].value > snarray[j + 1].value) {
                                    Thread.sleep(SPEED);
                                    swap(snarray[j], snarray[j + 1]);
                                    SortNode temp = snarray[j];
                                    snarray[j] = snarray[j + 1];
                                    snarray[j + 1] = temp;
                                }
                            }
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        });
        btninsert.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                new Thread(() -> {
                    btninsert.setDisable(true);
                    btnbubble.setDisable(true);
                    btnquick.setDisable(true);
                    try {
                            int n = snarray.length;
                            for (int i = 1; i < n; ++i) {
                                SortNode key = snarray[i];
                                int j = i - 1;
                                while (j >= 0 && snarray[j].value > key.value) {
                                    SortNode temp = snarray[j+1];
                                    Thread.sleep(SPEED);
                                    swap(snarray[j+1], snarray[j]);
                                    snarray[j + 1] = snarray[j];
                                    snarray[j] = temp;
                                    j = j - 1;
                                    for(int k = 0; k < snarray.length; k++){
                                        System.out.print(snarray[k].value + " ");
                                    }
                                    System.out.println();
                                }
                                Thread.sleep(SPEED);
                                swap(snarray[j+1], key);
                                snarray[j + 1] = key;
                            }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        });
        btnquick.setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                new Thread(() -> {
                    btninsert.setDisable(true);
                    btnbubble.setDisable(true);
                    btnquick.setDisable(true);
                    try {
                        quicksort(snarray, 0, snarray.length-1);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        });
        btnreset.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnbubble.setDisable(false);
                btninsert.setDisable(false);
                btnquick.setDisable(false);
            }
        });
        sliderSpeed.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                SPEED = (int) sliderSpeed.getValue();
            }
        });

    }


    public void shuffle() {
        array = generateRandomArray();
        viewPane.getChildren().removeAll(snarray);
        for (int i = 0; i < array.length; i++) {
            snarray[i] = new SortNode(25, array[i]*2, array[i], (i * 26));
        }
        viewPane.setArrayfield(snarray);
        viewPane.getChildren().addAll(snarray);
    }



    private void swap(SortNode sortNode, SortNode sortNode1) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {


                double dx = sortNode.getX() - sortNode1.getX();
                double tempx = sortNode.getX();
                double temp1x = sortNode1.getX();
                System.out.println("SortNode" + " x: " + sortNode.getX());
                sortNode.moveX(tempx, temp1x);
                System.out.println("SortNode" + " x: " + sortNode.getX());
                System.out.println("SortNode1" + " x: " + sortNode1.getX());
                sortNode1.moveX(temp1x, tempx);
                System.out.println("SortNode1" + " x: " + sortNode.getX());

            }
        });
    }

    int partition(SortNode arr[], int low, int high)
    {
        int pivot = arr[high].value;
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than the pivot
            if (arr[j].value < pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                Thread thread = new Thread();
                try {
                    thread.sleep(SPEED);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                swap(arr[i], arr[j]);
                SortNode temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        swap(arr[i+1], arr[high]);
        SortNode temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    void quicksort(SortNode arr[], int low, int high)
    {
        for(int i = 0;i<arr.length;i++){
            System.out.print(arr[i].value + " ");
        }
        System.out.println();
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            quicksort(arr, low, pi-1);
            quicksort(arr, pi+1, high);
        }
    }

    public int[] generateRandomArray(){
        int[] list = new int[12];
        Random random = new Random();

        for (int i = 0; i < 12; i++)
        {
            list[i]=(random.nextInt(99));
        }
        return list;
    }



}
