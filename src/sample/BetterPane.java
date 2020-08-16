package sample;

import javafx.scene.layout.Pane;

public class BetterPane extends Pane {


    public void setArrayfield(SortNode[] arrayfield) {
        this.arrayfield = arrayfield;
    }

    public SortNode[] arrayfield;

    public void paint(){
        this.getChildren().clear();
        this.getChildren().addAll(arrayfield);

    }
}
