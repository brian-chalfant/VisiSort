package sample;

import javafx.animation.FillTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Comparator;

public class SortNode extends Rectangle {
    public int value;

    public SortNode(double width, double height, int value, double x) {
        super(width, height);
        super.setX(x);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void moveX(double from, double to) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(500), this);
        FillTransition ft = new FillTransition(Duration.millis(250), this, Color.BLACK, Color.BLUE);
        ft.setCycleCount(2);
        ft.setAutoReverse(true);
        this.setX(to);
        if (to == 0.0) {
            tt.setToX(this.getX());
        } else {
            tt.setToX(this.getX());
        }
        ft.play();
        tt.play();
    }
}
