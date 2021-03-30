package view;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public interface IView {

    Pane drawPane = null;
    ColorPicker colorPicker = null;
    ComboBox<Integer> comboBox = null;
    ArrayList<Double> patrat = new ArrayList<Double>();
    ArrayList<Double> trapez = new ArrayList<Double>();

    Pane getPane();

    void setPane(Pane newPane);

    ColorPicker getColorPicker();

    void setColorPicker(ColorPicker colorP);

    ComboBox<Integer> getComboBox();

    void setComboBox(ComboBox<Integer> comboB);

    ArrayList<Double> getPatrat();

    void setPatrat(ArrayList<Double> p);

    ArrayList<Double> getTrapez();

    void setTrapez(ArrayList<Double> t);
}
