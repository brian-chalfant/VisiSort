package sample;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

import java.util.Random;

/**
 * Controller for Visi-Sort Program: handles all user interactions with the User Interface and provides logic
 * for sorting algorithms
 * @author Brian Chalfant 2020
 */

public class Controller {
    public Button btnBubble;
    public Button btnInsert;
    public Button btnQuick;
    public Button btnReset;
    public Slider sliderSpeed;
    int[] array = new int[]{68, 90, 69, 56, 57, 32, 38, 65, 77, 62, 50, 8};
    SortNode[] sortNodeArray = new SortNode[array.length];
    public Pane viewPane;
    public int SPEED = 250;

    /**
     * Set initial conditions for Scene and establish Event Handlers for buttons and Slider
     */
    public void initialize() {
        for (int i = 0; i < array.length; i++) {
            // Create a new SortNode: use the following values
            // Width: 25 (aesthetics)
            // Height: Value of that node * 2, Diffentiates between nodes
            // Value: Random value from the array
            // X-Value: i * 26 to distribute over Pane, +10 to allow for border
            // Y-Value: allows for space below buttons
            sortNodeArray[i] = new SortNode(25, array[i]*2, array[i], (i * 26)+10, 10);
        }
        viewPane.getChildren().addAll(sortNodeArray);
        //Event Handler for Bubblesort Button, uses New Thread so that Application thread can update SortNodes
        //Sets the other Sort buttons to disabled so they can't be used until reset
        btnBubble.setOnMouseClicked((EventHandler<Event>) arg0 -> new Thread(() -> {
            btnInsert.setDisable(true);
            btnBubble.setDisable(true);
            btnQuick.setDisable(true);
            try {
                for (int i = 0; i < sortNodeArray.length; i++) {
                    for (int j = 0; j < sortNodeArray.length - 1; j++) {
                        if (sortNodeArray[j].getValue() > sortNodeArray[j + 1].getValue()) {
                            //This sets the speed of the Animation, the higher the number, the slower it goes
                            Thread.sleep(SPEED);
                            //Swap() handles the visual swap
                            swap(sortNodeArray[j], sortNodeArray[j + 1]);
                            //This is the swap in the array
                            SortNode temp = sortNodeArray[j];
                            sortNodeArray[j] = sortNodeArray[j + 1];
                            sortNodeArray[j + 1] = temp;
                        }
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());
        //Event Handler for Insertion Sort Button, uses New Thread so that Application thread can update SortNodes
        //Sets the other Sort buttons to disabled so they can't be used until reset
        btnInsert.setOnMouseClicked((EventHandler<Event>) arg0 -> new Thread(() -> {
            btnInsert.setDisable(true);
            btnBubble.setDisable(true);
            btnQuick.setDisable(true);
            try {
                    int n = sortNodeArray.length;
                    for (int i = 1; i < n; ++i) {
                        SortNode key = sortNodeArray[i];
                        int j = i - 1;
                        while (j >= 0 && sortNodeArray[j].getValue() > key.getValue()) {
                            SortNode temp = sortNodeArray[j+1];
                            //This sets the speed of the Animation, the higher the number, the slower it goes
                            Thread.sleep(SPEED);
                            //Visual Swap
                            swap(sortNodeArray[j+1], sortNodeArray[j]);
                            //Logical Swap
                            sortNodeArray[j + 1] = sortNodeArray[j];
                            sortNodeArray[j] = temp;
                            j = j - 1;

                        }
                        Thread.sleep(SPEED);
                        swap(sortNodeArray[j+1], key);
                        sortNodeArray[j + 1] = key;
                    }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());
        //Event Handler for Quicksort Button, uses New Thread so that Application thread can update SortNodes
        //Sets the other Sort buttons to disabled so they can't be used until reset
        btnQuick.setOnMouseClicked((EventHandler<Event>) arg0 -> new Thread(() -> {
            btnInsert.setDisable(true);
            btnBubble.setDisable(true);
            btnQuick.setDisable(true);
            try {
                //calls quicksort for entire array
                quicksort(sortNodeArray, 0, sortNodeArray.length-1);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start());
        //Event Handler for Reset button. Enables Sort buttons and calls shuffle()
        btnReset.setOnMouseClicked(event -> {
            btnBubble.setDisable(false);
            btnInsert.setDisable(false);
            btnQuick.setDisable(false);
            shuffle();
        });
        //Event Listener for the Slider control.  Sets the SPEED variable based on position
        //The higher the number, the slower the animation will go
        sliderSpeed.valueProperty().addListener((observable, oldValue, newValue) ->
                SPEED = ((int) sliderSpeed.getValue()));

    }

    /**
     * Shuffle: the name is misleading.  it doesn't actually shuffle. it generates a whole new SortNodeArray
     * will all new Random Numbers
     */
    public void shuffle() {
        array = generateRandomArray();
        //Clears the Array from the Pane
        viewPane.getChildren().removeAll(sortNodeArray);
        for (int i = 0; i < array.length; i++) {
            // Create a new SortNode: use the following values
            // Width: 25 (aesthetics)
            // Height: Value of that node * 2, Diffentiates between nodes
            // Value: Random value from the array
            // X-Value: i * 26 to distribute over Pane, +10 to allow for border
            // Y-Value: allows for space below buttons
            sortNodeArray[i] = new SortNode(25, array[i]*2, array[i], (i * 26), 10);
        }
        //Adds the Array to the Pane
        viewPane.getChildren().addAll(sortNodeArray);
    }


    /**
     * Swap:  This is where the magic happens.  The X values of the two nodes are swapped
     * and the SortNode's moveX method is called to move the SortNode to it's new place
     * It's important that we use Platform.runLater() here so that the Animation will be displayed
     * @param sortNode first SortNode
     * @param sortNode1 second SortNode
     */
    private void swap(SortNode sortNode, SortNode sortNode1) {
        Platform.runLater(() -> {

            double tempX = sortNode.getX();
            double temp1x = sortNode1.getX();
            sortNode.moveX(temp1x, SPEED);
            sortNode1.moveX(tempX, SPEED);
        });
    }

    /**
     * Partition:  Used for Quicksort Algorithm, Divides an array into new partitions
     * @param arr  SortNode Array The array
     * @param low  Int Lowerbound index
     * @param high int upperbound index
     * @return int pivot point
     */
    int partition(SortNode[] arr, int low, int high)
    {
        int pivot = arr[high].getValue();
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than the pivot
            if (arr[j].getValue() < pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                try {
                    Thread.sleep(SPEED);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                swap(arr[i], arr[j]);
                SortNode temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high]
        swap(arr[i+1], arr[high]);
        SortNode temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }


    /**
     * Quicksort: the main method that implements Quicksort
     * @param arr SortNode Array
     * @param low int lowerbound index
     * @param high int upperbound index
     */
    void quicksort(SortNode[] arr, int low, int high)
    {
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

    /**
     * generateRandomArray: Generates Random Array of Integers form 0 to 99
     * @return Int[] Array
     */
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
