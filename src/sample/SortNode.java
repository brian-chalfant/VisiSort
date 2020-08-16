package sample;

import javafx.animation.FillTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * SortNode is a Rectangle, but it can store a value
 * and has one method moveX which animates its move from current position to X
 */
public class SortNode extends Rectangle {
    private int value;

    /**
     * Constructor
     * @param width  Width of the SortNode's Rectangle
     * @param height Height of the SortNode's Rectangle
     * @param value  Value of the SortNode
     * @param x X-value of the SortNode
     * @param y Y-Value of the SortNode
     */
    public SortNode(double width, double height, int value, double x, double y) {
        super(width, height);
        super.setX(x);
        super.setY(y);
        this.value = value;
    }

    /**
     * GetValue: Returns the value of the SortNode
     * @return int value
     */
    public int getValue() {
        return value;
    }

    /**
     * moveX: transitions the SortNode to a point X
     * @param to int x-value in
     * @param SPEED int speed at which to move in millis
     */
    public void moveX(double to, int SPEED) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(SPEED), this);
        FillTransition ft = new FillTransition(Duration.millis(SPEED), this, Color.BLACK, Color.BLUE);
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        this.setX(to);
        tt.setToX(this.getX());
        ft.play();
        tt.play();
    }
}
