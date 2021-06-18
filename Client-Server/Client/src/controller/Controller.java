package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import java.io.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    public CheckBox butonLungimileLaturilor;
    public CheckBox butonMasurileUnghiurilor;
    public CheckBox butonPerimetru;
    public CheckBox butonAria;
    public CheckBox butonRazaCerculuiCircumscris;
    public CheckBox butonRazaCerculuiInscris;
    public CheckBox butonPunctulNewton;
    public CheckBox butonPunctulMiquel;
    public CheckBox butonPunctulMathot;
    public CheckBox butonDiagonale;
    public CheckBox butonBimediane;
    public CheckBox butonBisectoare;
    public CheckBox butonDreaptaNewton;
    public CheckBox butonDreaptaGauss;
    public CheckBox butonDreaptaAubert;
    public CheckBox butonCerculCircumscris;
    public CheckBox butonCerculInscris;

    @FXML
    private Pane drawPane;
    @FXML
    ColorPicker colorPicker;
    @FXML
    ComboBox<Integer> comboBox = new ComboBox<>();
    @FXML
    Button butonTrapez;
    @FXML
    Button butonPatrat;
    protected ObservableList<Integer> list = FXCollections.observableArrayList(1, 2, 3, 4, 5);
    protected ArrayList<Double> patrat = new ArrayList<>();
    protected ArrayList<Double> trapez = new ArrayList<>();
    private int count;
    private ArrayList<Double> puncte = new ArrayList<>();
    private PatrulaterDesenat patrulaterDesenat;
    private Stage stage;
    private Limba limba;

    private IMesaj mesaj;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) { this.stage = stage; }

    public Pane getDrawPane() {
        return drawPane;
    }

    public PatrulaterDesenat getPatrulaterDesenat() {
        return patrulaterDesenat;
    }

    public ArrayList<Double> getPuncte() {
        return puncte;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setValue(1);
        comboBox.setItems(list);
        colorPicker.setValue(Color.BLACK);
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

        patrulaterDesenat = new PatrulaterDesenat();
        limba = new Limba();
        mesaj = new Mesaj();
    }

    public void mouseCliked(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        ArrayList<Double> puncte = this.puncte;
        if (count == 0) {
            puncte.add(x);
            puncte.add(y);
            Label label1 = new Label("A");
            label1.relocate(x - 10, y - 5);
            drawPane.getChildren().add(label1);
        } else if (count == 1) {
            puncte.add(x);
            puncte.add(y);
            Label label1 = new Label("B");
            label1.relocate(x + 5, y - 5);
            drawPane.getChildren().add(label1);
            Line AB = new Line(puncte.get(0), puncte.get(1), x, y);
            AB.setStrokeWidth(comboBox.getSelectionModel().getSelectedItem());
            AB.setStroke(colorPicker.getValue());
            drawPane.getChildren().add(AB);
        } else if (count == 2) {
            Line BC = new Line(puncte.get(2), puncte.get(3), x, y);
            BC.setStrokeWidth(comboBox.getSelectionModel().getSelectedItem());
            BC.setStroke(colorPicker.getValue());
            drawPane.getChildren().add(BC);
            Label label1 = new Label("C");
            label1.relocate(x + 5, y - 5);
            drawPane.getChildren().add(label1);
            puncte.add(x);
            puncte.add(y);
        } else if (count == 3) {
            Line CD = new Line(puncte.get(4), puncte.get(5), x, y);
            CD.setStrokeWidth(comboBox.getSelectionModel().getSelectedItem());
            CD.setStroke(colorPicker.getValue());
            drawPane.getChildren().add(CD);
            Line DA = new Line(x, y, puncte.get(0), puncte.get(1));
            DA.setStrokeWidth(comboBox.getSelectionModel().getSelectedItem());
            DA.setStroke(colorPicker.getValue());
            drawPane.getChildren().add(DA);
            Label label1 = new Label("D");
            label1.relocate(x - 10, y - 5);
            drawPane.getChildren().add(label1);
            puncte.add(x);
            puncte.add(y);
        }
        count++;
    }

    public void butonConcav(ActionEvent actionEvent) throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle(Limba.getBundle().getString("titluConcav"));
        dialog.setX(950);
        dialog.setY(300);
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        if (puncte.size() > 0) {
            dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("ui.text.concavBox") ));
            boolean value = mesaj.concavSauConvex( puncte);
            if (!value) {
                dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("ui.text.esteConcav")));
            } else {
                dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("ui.text.nuEsteConcav")));
            }
        } else {
            dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("ui.text.desenatiPatrulater")));
        }
        Scene dialogScene = new Scene(dialogVbox, 370, 170);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    public void butonConvex(ActionEvent actionEvent) throws IOException {
        final Stage dialog = new Stage();
        dialog.setTitle(Limba.getBundle().getString("titluConvex"));
        dialog.setX(950);
        dialog.setY(300);
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        if (puncte.size() > 0) {
            dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("ui.text.convexBox")));

            boolean value = mesaj.concavSauConvex( puncte);

            if (value) {
                dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("ui.text.esteConvex") ));
            } else {
                dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("ui.text.nuEsteConvex") ));
            }
            Scene dialogScene = new Scene(dialogVbox, 370, 170);
            dialog.setScene(dialogScene);
        } else {
            dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("ui.text.desenatiPatrulater")));
            Scene dialogScene = new Scene(dialogVbox, 250, 70);
            dialog.setScene(dialogScene);
        }
        dialog.show();
    }

    public void butonInscriptibil(ActionEvent actionEvent) throws IOException {
        if (puncte.size() > 0) {
            final Stage dialog = new Stage();
            dialog.setTitle(Limba.getBundle().getString("titluInscriptibil"));
            dialog.setX(950);
            dialog.setY(300);
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("inscriptibilBox") ));

            boolean value = mesaj.concavSauConvex(puncte);
            boolean inscriptibil = mesaj.inscriptibil( puncte);

            if (value) {
                if (inscriptibil) {
                    dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("esteInscriptibil") ));
                } else {
                    dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("nuEsteInscriptibil") ));
                }
            } else {
                dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("nuEsteInscriptibilConcav")));
            }
            Scene dialogScene = new Scene(dialogVbox, 430, 170);
            dialog.setScene(dialogScene);
            dialog.show();
        }
    }

    public void butonCircumscriptibil(ActionEvent actionEvent) throws IOException {
        if (puncte.size() > 0) {
            final Stage dialog = new Stage();
            dialog.setTitle(Limba.getBundle().getString("titluCircumscriptibil"));
            dialog.setX(950);
            dialog.setY(300);
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text( Limba.getBundle().getString("circumscriptibilBox")));

            boolean value = mesaj.concavSauConvex(puncte);
            boolean circumscriptibil = mesaj.circumscriptibil( puncte);

            if (value) {
                if (circumscriptibil) {
                    dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("esteCircumscriptibil") ));
                } else {
                    dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("nuEsteCircumscriptibil") ));
                }
            } else {
                dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("nuEsteCircumscriptibilConcav") ));
            }
            Scene dialogScene = new Scene(dialogVbox, 440, 170);
            dialog.setScene(dialogScene);
            dialog.show();
        }
    }

    public void draw(ArrayList<Double> v) {
        Line AB = new Line(v.get(0), v.get(1), v.get(2), v.get(3));
        AB.setStrokeWidth(comboBox.getSelectionModel().getSelectedItem());
        AB.setStroke(colorPicker.getValue());
        drawPane.getChildren().add(AB);
        Line BC = new Line(v.get(2), v.get(3), v.get(4), v.get(5));
        BC.setStrokeWidth(comboBox.getSelectionModel().getSelectedItem());
        BC.setStroke(colorPicker.getValue());
        drawPane.getChildren().add(BC);
        Line CD = new Line(v.get(4), v.get(5), v.get(6), v.get(7));
        CD.setStrokeWidth(comboBox.getSelectionModel().getSelectedItem());
        CD.setStroke(colorPicker.getValue());
        drawPane.getChildren().add(CD);
        Line DA = new Line(v.get(6), v.get(7), v.get(0), v.get(1));
        DA.setStrokeWidth(comboBox.getSelectionModel().getSelectedItem());
        DA.setStroke(colorPicker.getValue());
        drawPane.getChildren().add(DA);
        Label label1 = new Label("A");
        label1.relocate(v.get(0) - 10, v.get(1) - 10);
        drawPane.getChildren().add(label1);
        Label label3 = new Label("B");
        label3.relocate(v.get(2) + 5, v.get(3) - 5);
        drawPane.getChildren().add(label3);
        Label label4 = new Label("C");
        label4.relocate(v.get(4) + 5, v.get(5) + 5);
        drawPane.getChildren().add(label4);
        Label label2 = new Label("D");
        label2.relocate(v.get(6) - 5, v.get(7) + 5);
        drawPane.getChildren().add(label2);
    }

    public void ButonSalvare(ActionEvent actionEvent) throws IOException {
        if (this.puncte.size() > 0) {
            mesaj.salvareInFisier(puncte);
        }
    }

    public void butonIncarcare(ActionEvent actionEvent) throws IOException {
        if (this.puncte.size() == 0) {
            ArrayList<Double> cord = mesaj.citireDinFisier(puncte);
            draw(cord);
            this.puncte = cord;
            count = 4;
        }
    }

    public void butonStergere(ActionEvent actionEvent) {
        if (this.puncte.size() > 0) {
            drawPane.getChildren().clear();
            this.puncte.clear();
            count = 0;
        }
    }

    public void butonTrapez(ActionEvent actionEvent) {
        if (this.puncte.size() == 0) {
            draw(trapez);
            this.puncte = new ArrayList<>(trapez);
            count = 4;
        }
    }

    public void butonPatrat(ActionEvent actionEvent) {
        if (puncte.size() == 0) {
            draw(patrat);
            this.puncte = new ArrayList<>(patrat);
            count = 4;
        }
    }

    public void butonLungimileLaturilor(ActionEvent actionEvent) throws IOException {
        if (butonLungimileLaturilor.isSelected()) {
            if (puncte.size() > 0) {

                ArrayList<Double> laturi = mesaj.lungimileLAturilor(puncte);

                final Stage dialog = new Stage();
                dialog.setTitle("Lungimile laturilor");
                dialog.setX(950);
                dialog.setY(300);
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                DecimalFormat df = new DecimalFormat("#.##");
                dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("lungimileLaturilor") + "\n   AB = " + df.format(laturi.get(0)) + "\n   BC = " + df.format(laturi.get(1)) +
                        "\n   CD = " + df.format(laturi.get(2)) + "\n   DA = " + df.format(laturi.get(3))));
                Scene dialogScene = new Scene(dialogVbox, 370, 170);
                dialog.setScene(dialogScene);
                dialog.show();
                butonLungimileLaturilor.setSelected(false);
            }
        }
    }

    public void butonMasurileUnghiurilor(ActionEvent actionEvent) throws IOException {
        if (butonMasurileUnghiurilor.isSelected()) {
            if (puncte.size() > 0) {

                ArrayList<Double> unghi = mesaj.masurileUnghiurilor(puncte);

                final Stage dialog = new Stage();
                dialog.setTitle("Masurile unghiurilor");
                dialog.setX(950);
                dialog.setY(300);
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                DecimalFormat df = new DecimalFormat("#.##");
                dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("masurileUnghiurilor") + " \n   ∠A = " + df.format(unghi.get(0)) + "\n   ∠B = " + df.format(unghi.get(1)) +
                        "\n   ∠C = " + df.format(unghi.get(2)) + "\n   ∠D = " + df.format(unghi.get(3))));
                Scene dialogScene = new Scene(dialogVbox, 370, 170);
                dialog.setScene(dialogScene);
                dialog.show();
                butonMasurileUnghiurilor.setSelected(false);
            }
        }
    }

    public void butonPerimetru(ActionEvent actionEvent) throws IOException {
        if (butonPerimetru.isSelected()) {
            if (puncte.size() > 0) {
                double perim = mesaj.perimetru(puncte);
                final Stage dialog = new Stage();
                dialog.setTitle(Limba.getBundle().getString("titluPerimetru"));
                dialog.setX(950);
                dialog.setY(300);
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                DecimalFormat df = new DecimalFormat("#.##");
                dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("perimetruBox") + " \n\n  P = AB + BC + CD + DA = " + df.format(perim)));
                Scene dialogScene = new Scene(dialogVbox, 270, 90);
                dialog.setScene(dialogScene);
                dialog.show();
                butonPerimetru.setSelected(false);
            }
        }
    }

    public void butonRazaCerculuiCircumscris(ActionEvent actionEvent) throws IOException {
        if (butonRazaCerculuiCircumscris.isSelected()) {
            if (puncte.size() > 0) {

                ArrayList<Double> r = mesaj.razaCerculuiCircumscris(puncte);
                boolean circumscriptibil = mesaj.circumscriptibil(puncte);

                final Stage dialog = new Stage();
                dialog.setTitle("Raza cercului circumscris.");
                dialog.setX(950);
                dialog.setY(300);
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                if (circumscriptibil) {
                    Line c = new Line(puncte.get(0), puncte.get(1), r.get(0), r.get(1));
                    c.setStroke(Color.RED);
                    patrulaterDesenat.setRazaCerculuiCircumscris(c);
                    drawPane.getChildren().add(c);
                    DecimalFormat df = new DecimalFormat("#.##");
                    dialogVbox.getChildren().add(new Text( Limba.getBundle().getString("razaCircumscrisBox") + df.format(r.get(2))));
                } else {
                    dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("nuEsteCircumscriptibil")));
                }
                Scene dialogScene = new Scene(dialogVbox, 200, 60);
                dialog.setScene(dialogScene);
                dialog.show();
            }
        }else{
            drawPane.getChildren().remove(patrulaterDesenat.getRazaCerculuiCircumscris());
            drawPane.getChildren().remove(patrulaterDesenat.getCercCircumscris());
        }
    }

    public void butonRazaCerculuiInscris(ActionEvent actionEvent) throws IOException {

    }

    public void butonAria(ActionEvent actionEvent) throws IOException {
        if (butonAria.isSelected()) {
            if (puncte.size() > 0) {
                double aria = mesaj.aria(puncte);
                final Stage dialog = new Stage();
                dialog.setTitle(Limba.getBundle().getString("titluAria"));
                dialog.setX(950);
                dialog.setY(300);
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                DecimalFormat df = new DecimalFormat("#.##");
                dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("ariaBox") + " \n\n  A = " + df.format(aria)));
                Scene dialogScene = new Scene(dialogVbox, 270, 90);
                dialog.setScene(dialogScene);
                dialog.show();
                butonAria.setSelected(false);
            }
        }
    }

    public void butonPunctulNewton(ActionEvent actionEvent) throws IOException {
        if (butonPunctulNewton.isSelected()) {
            if (puncte.size() > 0) {
                final Stage dialog = new Stage();
                dialog.setTitle(Limba.getBundle().getString("punctulNewton"));
                dialog.setX(950);
                dialog.setY(300);
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("textNewton")));

                boolean circumscriptibil = mesaj.circumscriptibil(puncte);

                if (!circumscriptibil) {
                    dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("textNewton2")));
                }
                Scene dialogScene = new Scene(dialogVbox, 300, 150);
                dialog.setScene(dialogScene);
                dialog.show();
                butonPunctulNewton.setSelected(false);
            }
        }
    }

    public void butonPunctulMiquel(ActionEvent actionEvent) throws IOException {
        if (butonPunctulMiquel.isSelected()) {
            if (puncte.size() > 0) {
                final Stage dialog = new Stage();
                dialog.setTitle(Limba.getBundle().getString("punctulMiquel"));
                dialog.setX(950);
                dialog.setY(300);
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("textMiquel")));
                boolean value = mesaj.concavSauConvex( puncte);
                if (!value) {
                    dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("textMiquel2")));
                }
                Scene dialogScene = new Scene(dialogVbox, 300, 150);
                dialog.setScene(dialogScene);
                dialog.show();
                butonPunctulMiquel.setSelected(false);
            }
        }
    }

    public void butonPunctulMathot(ActionEvent actionEvent) throws IOException {
        if (butonPunctulMathot.isSelected()) {
            if (puncte.size() > 0) {
                final Stage dialog = new Stage();
                dialog.setTitle(Limba.getBundle().getString("punctulMathot"));
                dialog.setX(950);
                dialog.setY(300);
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("textMathot")));
                boolean inscriptibil = mesaj.inscriptibil(puncte);
                if (!inscriptibil) {
                    dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("textMathot2")));
                }
                Scene dialogScene = new Scene(dialogVbox, 300, 140);
                dialog.setScene(dialogScene);
                dialog.show();
                butonPunctulMiquel.setSelected(false);
            }
        }
    }

    public void butonDreaptaNewton(ActionEvent actionEvent) throws IOException {
        if (butonDreaptaNewton.isSelected()) {
            if (puncte.size() > 0) {
                final Stage dialog = new Stage();
                dialog.setTitle("Dreapta lui Newton");
                dialog.setX(950);
                dialog.setY(300);
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("Mijloacele diagonalelor unui patrulater circumscriptibil şi \n" +
                        "centrul cercului înscris sunt situate pe aceeaşi dreaptă,\nnumită dreapta lui Newton-Gauss.\n"));
                boolean circumscriptibil = mesaj.circumscriptibil(puncte);
                if (!circumscriptibil) {
                    dialogVbox.getChildren().add(new Text("Dreapta nu poate fi calculata deoarece patrulateru\n nu este circumscriptibil. \n"));
                }
                Scene dialogScene = new Scene(dialogVbox, 300, 150);
                dialog.setScene(dialogScene);
                dialog.show();
                butonDreaptaNewton.setSelected(false);
            }
        }
    }

    public void butonDreaptaGauss(ActionEvent actionEvent) {
        if (butonDreaptaGauss.isSelected()) {
            if (puncte.size() > 0) {
                final Stage dialog = new Stage();
                dialog.setTitle("Dreapta lui Gauss");
                dialog.setX(950);
                dialog.setY(300);
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("ABCD este un patrulater oarecare. " +
                        "\nMijloacele diagonalelor patrulaterului complet ABCD sunt coliniare." +
                        "\nDreapta pe care sunt situate se numeste dreapta lui Gauss.\n\n" +
                        "Nota: Patrulaterul complet are 3 diagonale: pe langa cele doua \nobisnuite, a treia este data de intersectiile laturilor AB cu CD,\nrespectiv AB cu BC."));
                Scene dialogScene = new Scene(dialogVbox, 360, 120);
                dialog.setScene(dialogScene);
                dialog.show();
                butonDreaptaGauss.setSelected(false);
            }
        }
    }

    public void butonDreaptaAubert(ActionEvent actionEvent) {
        if (butonDreaptaAubert.isSelected()) {
            if (puncte.size() > 0) {
                final Stage dialog = new Stage();
                dialog.setTitle("Dreapta lui Aubert");
                dialog.setX(950);
                dialog.setY(300);
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("ABCDEF este un patrulater complet. \nOrtocentrele triunghiurilor BEC, DCF, ABF, ADE sunt coliniare.\n" +
                        "Dreapta pe care sunt situate se numeste dreapta lui Aubert.\n\n" +
                        "Nota: Patrulaterul complet ABCDEF se obtine dintr-un patrulater \nABCD prin punerea in evidenta a punctelor de intersectie E si F\na laturilor opuse.\n"));
                Scene dialogScene = new Scene(dialogVbox, 350, 120);
                dialog.setScene(dialogScene);
                dialog.show();
                butonDreaptaAubert.setSelected(false);
            }
        }
    }

    public void butonBisectoare(ActionEvent actionEvent) {
        if(butonBisectoare.isSelected()) {
            if (this.puncte.size() > 0) {
                final Stage dialog = new Stage();
                dialog.setTitle("Bisectoare");
                dialog.setX(950);
                dialog.setY(300);
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("Bisectoarea unui unghi este semidreapta cu originea în vârful unghiului,\n  care împarte acest unghi în alte două unghiuri de măsuri egale.\n"));
                Scene dialogScene = new Scene(dialogVbox, 410, 110);
                dialog.setScene(dialogScene);
                dialog.show();
                butonBisectoare.setSelected(false);
            }
        }
    }

    public void butonBimediane(ActionEvent actionEvent) throws IOException {
        if (butonBimediane.isSelected()) {
            if (this.puncte.size() > 0) {
                final Stage dialog = new Stage();
                dialog.setTitle(Limba.getBundle().getString("titluBimediane"));
                dialog.setX(950);
                dialog.setY(300);
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("bimedianeBox")));
                Scene dialogScene = new Scene(dialogVbox, 410, 110);
                dialog.setScene(dialogScene);
                dialog.show();
                Label[] litere = new Label[4];

                ArrayList<Double> bimediane = mesaj.bimediane(puncte);

                Line AC = new Line(bimediane.get(0), bimediane.get(1), bimediane.get(2), bimediane.get(3));
                AC.setStroke(Color.RED);
                drawPane.getChildren().add(AC);
                Line BD = new Line(bimediane.get(4), bimediane.get(5), bimediane.get(6), bimediane.get(7));
                BD.setStroke(Color.RED);
                drawPane.getChildren().add(BD);
                Label label1 = new Label("X");
                label1.relocate(bimediane.get(0) - 5, bimediane.get(1) - 10.0);
                drawPane.getChildren().add(label1);
                litere[0] = label1;
                Label label3 = new Label("Y");
                label3.relocate(bimediane.get(2) + 5, bimediane.get(3) - 5);
                drawPane.getChildren().add(label3);
                litere[1] = label3;
                Label label4 = new Label("Z");
                label4.relocate(bimediane.get(4) + 5, bimediane.get(5) + 5);
                drawPane.getChildren().add(label4);
                litere[2] = label4;
                Label label2 = new Label("T");
                label2.relocate(bimediane.get(6) - 5, bimediane.get(7) + 5);
                drawPane.getChildren().add(label2);
                litere[3] = label2;
                Line[] bimedi = new Line[2];
                bimedi[0] = AC;
                bimedi[1] = BD;
                patrulaterDesenat.setBimediane(bimedi);
                patrulaterDesenat.setLitere(litere);
            }
        }else {
                drawPane.getChildren().remove(patrulaterDesenat.getBimediane()[0]);
                drawPane.getChildren().remove(patrulaterDesenat.getBimediane()[1]);
                for (int i = 0; i < 4; i++) {
                    drawPane.getChildren().remove(patrulaterDesenat.getLitere()[i]);
                }
            }
    }

    public void butonDiagonale(ActionEvent actionEvent) {
        if(butonDiagonale.isSelected()) {
            if (this.puncte.size() > 0) {
                final Stage dialog = new Stage();
                dialog.setTitle("Diagonale");
                dialog.setX(950);
                dialog.setY(300);
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("Diagonala este segmentul de legătură dintre două vârfuri opuse ale unui patrulater.\n"));
                dialogVbox.getChildren().add(new Text("Diagonalele sunt AC si BD. "));
                Scene dialogScene = new Scene(dialogVbox, 450, 110);
                dialog.setScene(dialogScene);
                dialog.show();
                Line AC = new Line(puncte.get(0), puncte.get(1), puncte.get(4), puncte.get(5));
                AC.setStroke(Color.RED);
                drawPane.getChildren().add(AC);
                Line BD = new Line(puncte.get(2), puncte.get(3), puncte.get(6), puncte.get(7));
                BD.setStroke(Color.RED);
                drawPane.getChildren().add(BD);
                Line[] diagonale = new Line[2];
                diagonale[0] = AC;
                diagonale[1] = BD;
                patrulaterDesenat.setDiagonale(diagonale);
            }
        }else{
            drawPane.getChildren().remove(patrulaterDesenat.getDiagonale()[0]);
            drawPane.getChildren().remove(patrulaterDesenat.getDiagonale()[1]);
        }
    }

    public void butonCerculInscris(ActionEvent actionEvent) {
    }

    public void butonCerculCircumscris(ActionEvent actionEvent) throws IOException {
        if (butonCerculCircumscris.isSelected()) {
            if (puncte.size() > 0) {
                ArrayList<Double> cerc = mesaj.razaCerculuiCircumscris(puncte);
                boolean circumscriptibil = mesaj.circumscriptibil(puncte);
                if (circumscriptibil) {
                    Circle cr = new Circle(cerc.get(0), cerc.get(1), cerc.get(2));
                    cr.setFill(Color.TRANSPARENT);
                    cr.setStroke(Color.RED);
                    drawPane.getChildren().add(cr);
                    patrulaterDesenat.setCercCircumscris(cr);
                } else {
                    final Stage dialog = new Stage();
                    dialog.setTitle(Limba.getBundle().getString("cercCircumscris"));
                    dialog.setX(950);
                    dialog.setY(300);
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    VBox dialogVbox = new VBox(20);
                    dialogVbox.getChildren().add(new Text(Limba.getBundle().getString("textCircumscris")));
                    Scene dialogScene = new Scene(dialogVbox, 200, 60);
                    dialog.setScene(dialogScene);
                    dialog.show();
                }
            }
        } else {
            drawPane.getChildren().remove(patrulaterDesenat.getCercCircumscris());
        }
    }

    public void butonRomana(ActionEvent actionEvent) {
        Limba.setObserverLimba(new ObserverLimba(this));
        limba.setLocale(new Locale("RO"),this);
    }

    public void butonEngleza(ActionEvent actionEvent) {
        Limba.setObserverLimba(new ObserverLimba(this));
        limba.setLocale(new Locale("EN"),this);
    }

    public void butonFranceza(ActionEvent actionEvent) {
        Limba.setObserverLimba(new ObserverLimba(this));
        limba.setLocale(new Locale("FR"),this);
    }

    public void butonSpaniola(ActionEvent actionEvent) {
        Limba.setObserverLimba(new ObserverLimba(this));
        limba.setLocale(new Locale("ES"),this);
    }
}
