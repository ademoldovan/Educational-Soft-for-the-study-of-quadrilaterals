package view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import presenter.Presenter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GUI extends Application implements Initializable, IView {

    @FXML
    Pane drawPane;
    @FXML
    ColorPicker colorPicker;
    @FXML
    ComboBox<Integer> comboBox;
    @FXML
    Button butonTrapez;
    @FXML
    Button butonPatrat;
    protected ObservableList<Integer> list = FXCollections.observableArrayList(1, 2, 3, 4, 5);
    protected ArrayList<Double> patrat = new ArrayList<>();
    protected ArrayList<Double> trapez = new ArrayList<>();

    private Presenter presenter;

    @Override
    public Pane getPane() {
        return this.drawPane;
    }

    @Override
    public void setPane(Pane newPane) {
        this.drawPane = newPane;
    }

    @Override
    public ColorPicker getColorPicker() {
        return this.colorPicker;
    }

    @Override
    public void setColorPicker(ColorPicker colorP) {
        this.colorPicker = colorP;
    }

    @Override
    public ComboBox<Integer> getComboBox() {
        return this.comboBox;
    }

    @Override
    public void setComboBox(ComboBox<Integer> comboB) {
        this.comboBox = comboB;
    }

    @Override
    public ArrayList<Double> getPatrat() {
        return new ArrayList<>(this.patrat);
    }

    @Override
    public void setPatrat(ArrayList<Double> p) {
        this.patrat = p;
    }

    @Override
    public ArrayList<Double> getTrapez() {
        return new ArrayList<>(this.trapez);
    }

    @Override
    public void setTrapez(ArrayList<Double> t) {
        this.trapez = t;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        presenter = new Presenter(this);
        comboBox.setValue(1);
        comboBox.setItems(list);
        colorPicker.setValue(Color.BLACK);
        //Creating a graphic (image)
        Image img = new Image(getClass().getResourceAsStream("patrat.png"));
        ImageView view = new ImageView(img);
        view.setFitHeight(37);
        view.setFitWidth(52);
        butonPatrat.setGraphic(view);
        Image img1 = new Image(getClass().getResourceAsStream("trapez.png"));
        ImageView view1 = new ImageView(img1);
        view1.setFitHeight(37);
        view1.setFitWidth(52);
        butonTrapez.setGraphic(view1);

        this.patrat.add(150.0);
        this.patrat.add(100.0);
        this.patrat.add(350.0);
        this.patrat.add(100.0);
        this.patrat.add(350.0);
        this.patrat.add(300.0);
        this.patrat.add(150.0);
        this.patrat.add(300.0);

        this.trapez.add(175.0);
        this.trapez.add(100.0);
        this.trapez.add(325.0);
        this.trapez.add(100.0);
        this.trapez.add(400.0);
        this.trapez.add(312.13);
        this.trapez.add(100.0);
        this.trapez.add(312.13);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Studiul patrulaterului");
        stage.setScene(scene);
        stage.show();
    }

    public void mouseCliked(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        presenter.desenarePatrulater(x, y);
    }

    public void butonConvex(ActionEvent actionEvent) {
        presenter.calculConvex();
    }

    public void butonConcav(ActionEvent actionEvent) {
        presenter.calculConcav();
    }

    public void butonInscriptibil(ActionEvent actionEvent) {
        presenter.calculInscriptibil();
    }

    public void butonCircumscriptibil(ActionEvent actionEvent) {
        presenter.calculCircumscriptibil();
    }

    public void ButonSalvare(ActionEvent actionEvent) {
        presenter.scriereXML();
    }

    public void butonStergere(ActionEvent actionEvent) {
        presenter.stergere();
    }

    public void butonPerimetru(ActionEvent actionEvent) {
        presenter.calculPerimetru();
    }

    public void butonAria(ActionEvent actionEvent) {
        presenter.calculAria();
    }

    public void butonLungimileLaturilor(ActionEvent actionEvent) {
        presenter.calculLaturi();
    }

    public void butonMasurileUnghiurilor(ActionEvent actionEvent) {
        presenter.calculUnghiuri();
    }

    public void butonRazaCerculuiInscris(ActionEvent actionEvent) {
        presenter.razaCerculuiInscris();
    }

    public void butonRazaCerculuiCircumscris(ActionEvent actionEvent) {
        presenter.razaCerculuiCircumscris();
    }

    public void butonPunctulNewton(ActionEvent actionEvent) {
        presenter.punctulNewton();
    }

    public void butonPunctulMiquel(ActionEvent actionEvent) {
        presenter.punctulMiquel();
    }

    public void butonPunctulMathot(ActionEvent actionEvent) {
        presenter.punctulMathot();
    }

    public void butonDreaptaNewton(ActionEvent actionEvent) {
        presenter.dreaptaNewton();
    }

    public void butonDreaptaGauss(ActionEvent actionEvent) {
        presenter.dreaptaGauss();
    }

    public void butonDreaptaAubert(ActionEvent actionEvent) {
        presenter.dreaptaAubert();
    }

    public void butonBisectoare(ActionEvent actionEvent) {
        presenter.bisectoare();
    }

    public void butonBimediane(ActionEvent actionEvent) {
        presenter.bimediane();
    }

    public void butonDiagonale(ActionEvent actionEvent) {
        presenter.diagonale();
    }

    public void butonCerculInscris(ActionEvent actionEvent) {
        presenter.calculCercInscris();
    }

    public void butonCerculCircumscris(ActionEvent actionEvent) {
        presenter.calculCercCircumscris();
    }

    public void butonTrapez(ActionEvent actionEvent) {
        presenter.trapez();
    }

    public void butonPatrat(ActionEvent actionEvent) {
        presenter.patrat();
    }

    public void butonIncarcare(ActionEvent actionEvent) {
        presenter.citireXML();
    }
}















