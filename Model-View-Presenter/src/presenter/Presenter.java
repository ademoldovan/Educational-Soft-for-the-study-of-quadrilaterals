package presenter;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import view.IView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Presenter {

    IView patrulaterV;
    private int count;
    private ArrayList<Double> puncte = new ArrayList<>();

    public Presenter(IView view) {
        this.patrulaterV = view;
        count = 0;
    }

    public Patrulater setPatrulater(ArrayList<Double> value) {
        Patrulater p = new Patrulater();
        p.setA(new Punct(value.get(0), value.get(1)));
        p.setB(new Punct(value.get(2), value.get(3)));
        p.setC(new Punct(value.get(4), value.get(5)));
        p.setD(new Punct(value.get(6), value.get(7)));
        return p;
    }

    public void desenarePatrulater(double x, double y) {
        ArrayList<Double> puncte = this.puncte;
        if (count == 0) {
            puncte.add(x);
            puncte.add(y);
            Label label1 = new Label("A");
            label1.relocate(x - 10, y - 5);
            patrulaterV.getPane().getChildren().add(label1);
        } else if (count == 1) {
            Line AB = new Line(puncte.get(0), puncte.get(1), x, y);
            AB.setStrokeWidth(patrulaterV.getComboBox().getSelectionModel().getSelectedItem());
            AB.setStroke(patrulaterV.getColorPicker().getValue());
            patrulaterV.getPane().getChildren().add(AB);
            Label label1 = new Label("B");
            label1.relocate(x + 5, y - 5);
            patrulaterV.getPane().getChildren().add(label1);
            puncte.add(x);
            puncte.add(y);
        } else if (count == 2) {
            Line BC = new Line(puncte.get(2), puncte.get(3), x, y);
            BC.setStrokeWidth(patrulaterV.getComboBox().getSelectionModel().getSelectedItem());
            BC.setStroke(patrulaterV.getColorPicker().getValue());
            patrulaterV.getPane().getChildren().add(BC);
            Label label1 = new Label("C");
            label1.relocate(x + 5, y - 5);
            patrulaterV.getPane().getChildren().add(label1);
            puncte.add(x);
            puncte.add(y);
        } else if (count == 3) {
            Line CD = new Line(puncte.get(4), puncte.get(5), x, y);
            CD.setStrokeWidth(patrulaterV.getComboBox().getSelectionModel().getSelectedItem());
            CD.setStroke(patrulaterV.getColorPicker().getValue());
            patrulaterV.getPane().getChildren().add(CD);
            Line DA = new Line(x, y, puncte.get(0), puncte.get(1));
            DA.setStrokeWidth(patrulaterV.getComboBox().getSelectionModel().getSelectedItem());
            DA.setStroke(patrulaterV.getColorPicker().getValue());
            patrulaterV.getPane().getChildren().add(DA);
            Label label1 = new Label("D");
            label1.relocate(x - 10, y - 5);
            patrulaterV.getPane().getChildren().add(label1);
            puncte.add(x);
            puncte.add(y);
        }
        count++;
    }

    public void calculPerimetru() {
        if (puncte.size() > 0) {
            Patrulater p = setPatrulater(this.puncte);
            double perim = p.perimetru(p);
            final Stage dialog = new Stage();
            dialog.setTitle("Perimetru");
            dialog.setX(950);
            dialog.setY(300);
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);
            DecimalFormat df = new DecimalFormat("#.##");
            dialogVbox.getChildren().add(new Text("Perimetrul este egal cu suma lungimilor laturilor. \n\n  P = AB + BC + CD + DA = " + df.format(perim)));
            Scene dialogScene = new Scene(dialogVbox, 270, 90);
            dialog.setScene(dialogScene);
            dialog.show();
        }
    }

    public void calculAria() {
        if (puncte.size() > 0) {
            Patrulater p = setPatrulater(this.puncte);
            double aria = p.aria(p);
            final Stage dialog = new Stage();
            dialog.setTitle("Aria");
            dialog.setX(950);
            dialog.setY(300);
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);
            DecimalFormat df = new DecimalFormat("#.##");
            dialogVbox.getChildren().add(new Text("Aria unui patrulater este egala cu suma ariilor \ntriunghiurilor în care se descompune poligonul.  \n\n  A = " + df.format(aria)));
            Scene dialogScene = new Scene(dialogVbox, 270, 90);
            dialog.setScene(dialogScene);
            dialog.show();
        }
    }

    public void calculConvex() {
        final Stage dialog = new Stage();
        dialog.setTitle("Patrulater convex");
        dialog.setX(950);
        dialog.setY(300);
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        if (puncte.size() > 0) {
            Patrulater p = setPatrulater(this.puncte);
            dialogVbox.getChildren().add(new Text("Patrulatere convexe au următoarele proprietăți:\n -diagonalele se intersectează în interiorul patrulaterului \n -segmentul format de două puncte situate în interiorul patrulaterului,\n   va fi în întregime situat în interiorul patrulaterului\n -toate unghiurile sunt mai mici decât 180 de grade.\n"));
            if (p.concavSauConvex(p)) {
                dialogVbox.getChildren().add(new Text("Patrulaterul desenat este convex."));
            } else {
                dialogVbox.getChildren().add(new Text("Patrulaterul desenat nu este convex."));
            }
            Scene dialogScene = new Scene(dialogVbox, 370, 170);
            dialog.setScene(dialogScene);
        } else {
            dialogVbox.getChildren().add(new Text("Desenati un patrulater."));
            Scene dialogScene = new Scene(dialogVbox, 250, 70);
            dialog.setScene(dialogScene);
        }
        dialog.show();
    }

    public void calculConcav() {
        final Stage dialog = new Stage();
        dialog.setTitle("Patrulater concav");
        dialog.setX(950);
        dialog.setY(300);
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        if (puncte.size() > 0) {
            Patrulater p = setPatrulater(this.puncte);
            dialogVbox.getChildren().add(new Text("Patrulatere concave au următoarele proprietăți:\n -diagonalele se intersectează în exteriorul patrulaterului \n -segmentul format de două puncte situate în interiorul patrulaterului,\n   poate fi situat atat în interiorul, cat si in exteriorul patrulaterului\n -are un unghi mai mare de 180 de grade.\n"));
            if (!p.concavSauConvex(p)) {
                dialogVbox.getChildren().add(new Text("Patrulaterul desenat este concav."));
            } else {
                dialogVbox.getChildren().add(new Text("Patrulaterul desenat nu este concav."));
            }
            Scene dialogScene = new Scene(dialogVbox, 370, 170);
            dialog.setScene(dialogScene);
        } else {
            dialogVbox.getChildren().add(new Text("Desenati un patrulater."));
            Scene dialogScene = new Scene(dialogVbox, 250, 70);
            dialog.setScene(dialogScene);
        }
        dialog.show();
    }

    public void calculInscriptibil() {
        if (puncte.size() > 0) {
            Patrulater p = setPatrulater(this.puncte);
            final Stage dialog = new Stage();
            dialog.setTitle("Patrulater inscriptibil");
            dialog.setX(950);
            dialog.setY(300);
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Patrulaterul care poate fi înscris intr-un cerc se numeşte patrulater inscriptibil." +
                    "\nFie ABCD un patrulater convex. Următoarele afirmaţii sunt echivalente:\n" +
                    "1) Unghiurile dintre diagonale si laturile opuse sunt congruente.\n" +
                    "2) Patrulaterul ABCD este inscriptibil.\n" +
                    "3) Are loc: m(A) + m(C) = m(B) + m(D)  \n" +
                    "    unde m(A) = masura unghiului A\n"));
            if (p.concavSauConvex(p)) {
                if (p.inscriptibil(p)) {
                    dialogVbox.getChildren().add(new Text("Patrulaterul desenat este inscriptibil."));
                } else {
                    dialogVbox.getChildren().add(new Text("Patrulaterul desenat nu este inscriptibil."));
                }
            } else {
                dialogVbox.getChildren().add(new Text("Patrulaterul desenat nu este inscriptibil deoarece este concav."));
            }
            Scene dialogScene = new Scene(dialogVbox, 430, 170);
            dialog.setScene(dialogScene);
            dialog.show();
        }
    }

    public void calculCircumscriptibil() {
        if (puncte.size() > 0) {
            Patrulater p = setPatrulater(this.puncte);
            final Stage dialog = new Stage();
            dialog.setTitle("Patrulater circumscriptibil");
            dialog.setX(950);
            dialog.setY(300);
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Patrulaterul în care se poate înscrie un cerc se numeşte patrulaterul circumscriptibil." +
                    "\nFie ABCD un patrulater convex. Următoarele afirmaţii sunt echivalente:\n" +
                    "1) Bisectoarele unghiurilor patrulaterului sunt concurente.\n" +
                    "2) Patrulaterul ABCD este circumscriptibil.\n" +
                    "3) Are loc: AB + CD = BC + DA  \n"));
            if (p.concavSauConvex(p)) {
                if (p.circumscriptibil(p)) {
                    dialogVbox.getChildren().add(new Text("Patrulaterul desenat este circumscriptibil."));
                } else {
                    dialogVbox.getChildren().add(new Text("Patrulaterul desenat nu este circumscriptibil."));
                }
            } else {
                dialogVbox.getChildren().add(new Text("Patrulaterul desenat nu este circumscriptibil deoarece este concav."));
            }
            Scene dialogScene = new Scene(dialogVbox, 440, 170);
            dialog.setScene(dialogScene);
            dialog.show();
        }
    }

    public void calculUnghiuri() {
        if (puncte.size() > 0) {
            Patrulater p = setPatrulater(this.puncte);
            ArrayList<Double> unghi = p.masurileUnghiurilor(p);
            final Stage dialog = new Stage();
            dialog.setTitle("Masurile unghiurilor");
            dialog.setX(950);
            dialog.setY(300);
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);
            DecimalFormat df = new DecimalFormat("#.##");
            dialogVbox.getChildren().add(new Text("Masurile unghiurilor: \n   ∠A = " + df.format(unghi.get(0)) + "\n   ∠B = " + df.format(unghi.get(1)) +
                    "\n   ∠C = " + df.format(unghi.get(2)) + "\n   ∠D = " + df.format(unghi.get(3))));
            Scene dialogScene = new Scene(dialogVbox, 370, 170);
            dialog.setScene(dialogScene);
            dialog.show();
        }
    }

    public void calculLaturi() {
        if (puncte.size() > 0) {
            Patrulater p = setPatrulater(this.puncte);
            ArrayList<Double> laturi = p.lungimileLaturilor(p);
            final Stage dialog = new Stage();
            dialog.setTitle("Lungimile laturilor");
            dialog.setX(950);
            dialog.setY(300);
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);
            DecimalFormat df = new DecimalFormat("#.##");
            dialogVbox.getChildren().add(new Text("Lungimile laturilor: \n   AB = " + df.format(laturi.get(0)) + "\n   BC = " + df.format(laturi.get(1)) +
                    "\n   CD = " + df.format(laturi.get(2)) + "\n   DA = " + df.format(laturi.get(3))));
            Scene dialogScene = new Scene(dialogVbox, 370, 170);
            dialog.setScene(dialogScene);
            dialog.show();
        }
    }

    public void calculCercCircumscris() {
        if (puncte.size() > 0) {
            Patrulater p = setPatrulater(this.puncte);
            Cerc c = p.cerculCircumscris(p);
            ArrayList<Double> cerc = new ArrayList<>();
            cerc.add(c.getCentru().getX());
            cerc.add(c.getCentru().getY());
            cerc.add(c.getRaza());
            if (p.circumscriptibil(p)) {
                Cerc ce = p.cerculCircumscris(p);
                Circle cr = new Circle(ce.getCentru().getX(), ce.getCentru().getY(), ce.getRaza());
                cr.setFill(Color.TRANSPARENT);
                cr.setStroke(Color.RED);
                patrulaterV.getPane().getChildren().add(cr);
            } else {
                final Stage dialog = new Stage();
                dialog.setTitle("Cercul circumscris.");
                dialog.setX(950);
                dialog.setY(300);
                dialog.initModality(Modality.APPLICATION_MODAL);
                VBox dialogVbox = new VBox(20);
                dialogVbox.getChildren().add(new Text("Patrulaterul nu este inscriptibil."));
                Scene dialogScene = new Scene(dialogVbox, 200, 60);
                dialog.setScene(dialogScene);
                dialog.show();
            }
        }
    }

    public void calculCercInscris() {
        if (puncte.size() > 0) {
            Patrulater p = setPatrulater(this.puncte);
        }

//        if(presenter.calculInscriptibil()) {
//            ArrayList<Double> cerc = presenter.calculCercInscris();
//            Circle c = new Circle(cerc.get(0), cerc.get(1), cerc.get(2));
//            c.setFill(Color.TRANSPARENT);
//            c.setStroke(Color.RED);
//            drawPane.getChildren().add(c);
//        }else {
//            final Stage dialog = new Stage();
//            dialog.setTitle("Cercul inscris.");
//            dialog.setX(950);
//            dialog.setY(300);
//            dialog.initModality(Modality.APPLICATION_MODAL);
//            VBox dialogVbox = new VBox(20);
//            dialogVbox.getChildren().add(new Text("Patrulaterul nu este circumscriptibil."));
//            Scene dialogScene = new Scene(dialogVbox, 190, 60);
//            dialog.setScene(dialogScene);
//            dialog.show();
//        }
    }

    public void razaCerculuiInscris() {
        if (puncte.size() > 0) {
            Patrulater p = setPatrulater(this.puncte);
            Cerc cerc = p.cerculInscris(p);
        }
//        if(presenter.calculInscriptibil()) {
//            Line c = new Line(this.puncte.get(0), this.puncte.get(1), this.puncte.get(4),this.puncte.get(5));
//            c.setStroke(Color.RED);
//            drawPane.getChildren().add(c);
//            final Stage dialog = new Stage();
//            dialog.setTitle("Raza cercului inscris.");
//            dialog.setX(950);
//            dialog.setY(300);
//            dialog.initModality(Modality.APPLICATION_MODAL);
//            VBox dialogVbox = new VBox(20);
//            DecimalFormat df = new DecimalFormat("#.##");
//            dialogVbox.getChildren().add(new Text("\nRaza cercului inscris: " + df.format(cerc.get(2))));
//            Scene dialogScene = new Scene(dialogVbox, 200, 60);
//            dialog.setScene(dialogScene);
//            dialog.show();
//        }else {
//            final Stage dialog = new Stage();
//            dialog.setTitle("Cercul inscris.");
//            dialog.setX(950);
//            dialog.setY(300);
//            dialog.initModality(Modality.APPLICATION_MODAL);
//            VBox dialogVbox = new VBox(20);
//            dialogVbox.getChildren().add(new Text("Patrulaterul nu este inscriptibil."));
//            Scene dialogScene = new Scene(dialogVbox, 200, 60);
//            dialog.setScene(dialogScene);
//            dialog.show();
//        }
    }

    public void razaCerculuiCircumscris() {
        if (puncte.size() > 0) {
            Patrulater p = setPatrulater(this.puncte);
            Cerc cerc = p.cerculCircumscris(p);
            final Stage dialog = new Stage();
            dialog.setTitle("Raza cercului circumscris.");
            dialog.setX(950);
            dialog.setY(300);
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);
            if (p.circumscriptibil(p)) {
                Line c = new Line(p.getA().getX(), p.getA().getY(), p.getC().getX(), p.getC().getY());
                c.setStroke(Color.RED);
                patrulaterV.getPane().getChildren().add(c);
                DecimalFormat df = new DecimalFormat("#.##");
                dialogVbox.getChildren().add(new Text("\nRaza cercului circumscris: " + df.format(cerc.getRaza())));
            } else {
                dialogVbox.getChildren().add(new Text("Patrulaterul nu este circumscriptibil."));
            }
            Scene dialogScene = new Scene(dialogVbox, 200, 60);
            dialog.setScene(dialogScene);
            dialog.show();
        }
    }

    public void punctulNewton() {
        if (puncte.size() > 0) {
            Patrulater p = setPatrulater(this.puncte);
            final Stage dialog = new Stage();
            dialog.setTitle("Punctul lui Newton");
            dialog.setX(950);
            dialog.setY(300);
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Intr-un patrulater circumscris unui cerc, diagonalele \n" +
                    "si segmentele determinate de punctele de tangenta sunt\nconcurente intr-un punct N, numit punctul lui Newton..\n"));
            if (!p.circumscriptibil(p)) {
                dialogVbox.getChildren().add(new Text("Punctul nu poate fi calculat deoarece patrulaterul nu \neste circumscriptibil. \n"));
            }
            Scene dialogScene = new Scene(dialogVbox, 300, 150);
            dialog.setScene(dialogScene);
            dialog.show();
        }
    }

    public void punctulMiquel() {
        if (puncte.size() > 0) {
            Patrulater p = setPatrulater(this.puncte);
            final Stage dialog = new Stage();
            dialog.setTitle("Punctul lui Miquel");
            dialog.setX(950);
            dialog.setY(300);
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("ABCD este un patrulater convex. \n" +
                    "  AB ∩ CD = {E}, AD ∩ BC = {F}.\nCercurile circumscrise triunghiurilor ADE, BCE, FCD, FAB\n" +
                    "au un punct comun M, numit punctul lui Miquel. \n"));
            if (!p.concavSauConvex(p)) {
                dialogVbox.getChildren().add(new Text("Punctul nu poate fi calculat deoarece patrulaterul nu \neste convex. \n"));
            }
            Scene dialogScene = new Scene(dialogVbox, 300, 150);
            dialog.setScene(dialogScene);
            dialog.show();
        }
    }

    public void punctulMathot() {
        if (puncte.size() > 0) {
            Patrulater p = setPatrulater(this.puncte);
            final Stage dialog = new Stage();
            dialog.setTitle("Punctul lui Mathot");
            dialog.setX(950);
            dialog.setY(300);
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Intr-un patrulater inscriptibil ,perpendicularele din\n" +
                    "mijloacele laturilor pe laturile opuse sunt concurente\n intr-un punct M, numit punctul lui Mathot.\n"));
            if (!p.inscriptibil(p)) {
                dialogVbox.getChildren().add(new Text("Punctul nu poate fi calculat deoarece patrulaterul nu \neste inscriptibil. \n"));
            }
            Scene dialogScene = new Scene(dialogVbox, 300, 140);
            dialog.setScene(dialogScene);
            dialog.show();
        }
    }

    public void dreaptaNewton() {
        if (puncte.size() > 0) {
            Patrulater p = setPatrulater(this.puncte);
            final Stage dialog = new Stage();
            dialog.setTitle("Dreapta lui Newton");
            dialog.setX(950);
            dialog.setY(300);
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Mijloacele diagonalelor unui patrulater circumscriptibil şi \n" +
                    "centrul cercului înscris sunt situate pe aceeaşi dreaptă,\nnumită dreapta lui Newton-Gauss.\n"));
            if (!p.circumscriptibil(p)) {
                dialogVbox.getChildren().add(new Text("Dreapta nu poate fi calculata deoarece patrulateru\n nu este circumscriptibil. \n"));
            }
            Scene dialogScene = new Scene(dialogVbox, 300, 150);
            dialog.setScene(dialogScene);
            dialog.show();
        }
    }

    public void dreaptaGauss() {
        if (puncte.size() > 0) {
            Patrulater p = setPatrulater(this.puncte);
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
        }
    }

    public void dreaptaAubert() {
        if (puncte.size() > 0) {
            Patrulater p = setPatrulater(this.puncte);
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
        }
    }

    public void bisectoare() {
        if(this.puncte.size() > 0) {
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
        }
    }

    public void bimediane() {
        if (this.puncte.size() > 0) {
            final Stage dialog = new Stage();
            dialog.setTitle("Bimediane");
            dialog.setX(950);
            dialog.setY(300);
            dialog.initModality(Modality.APPLICATION_MODAL);
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Bimediana este segmentul de dreaptă care uneşte mijloacele laturilor opuse.\n"));
            dialogVbox.getChildren().add(new Text("Bimedianele sunt XY si ZT. "));
            Scene dialogScene = new Scene(dialogVbox, 410, 110);
            dialog.setScene(dialogScene);
            dialog.show();

            Patrulater p = setPatrulater(this.puncte);
            ArrayList<Dreapta> bimediane = p.bimediane(p);
            Line AC = new Line(bimediane.get(0).getA().getX(), bimediane.get(0).getA().getY(), bimediane.get(0).getB().getX(), bimediane.get(0).getB().getY());
            AC.setStroke(Color.RED);
            patrulaterV.getPane().getChildren().add(AC);
            Line BD = new Line(bimediane.get(1).getA().getX(), bimediane.get(1).getA().getY(), bimediane.get(1).getB().getX(), bimediane.get(1).getB().getY());
            BD.setStroke(Color.RED);
            patrulaterV.getPane().getChildren().add(BD);
            Label label1 = new Label("X");
            label1.relocate(bimediane.get(0).getA().getX(), bimediane.get(0).getA().getY() - 10.0);
            patrulaterV.getPane().getChildren().add(label1);
            Label label3 = new Label("Y");
            label3.relocate(bimediane.get(0).getB().getX() + 5, bimediane.get(0).getB().getY() - 5);
            patrulaterV.getPane().getChildren().add(label3);
            Label label4 = new Label("Z");
            label4.relocate(bimediane.get(1).getA().getX() + 5, bimediane.get(1).getA().getY() + 5);
            patrulaterV.getPane().getChildren().add(label4);
            Label label2 = new Label("T");
            label2.relocate(bimediane.get(1).getB().getX() - 5, bimediane.get(1).getB().getY() + 5);
            patrulaterV.getPane().getChildren().add(label2);
        }
    }

    public void diagonale() {
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
            Patrulater p = setPatrulater(this.puncte);
            Line AC = new Line(p.getA().getX(), p.getA().getY(), p.getC().getX(), p.getC().getY());
            AC.setStroke(Color.RED);
            patrulaterV.getPane().getChildren().add(AC);
            Line BD = new Line(p.getB().getX(), p.getB().getY(), p.getD().getX(), p.getD().getY());
            BD.setStroke(Color.RED);
            patrulaterV.getPane().getChildren().add(BD);
        }
    }

    public void draw(ArrayList<Double> v) {
        Line AB = new Line(v.get(0), v.get(1), v.get(2), v.get(3));
        AB.setStrokeWidth(patrulaterV.getComboBox().getSelectionModel().getSelectedItem());
        AB.setStroke(patrulaterV.getColorPicker().getValue());
        patrulaterV.getPane().getChildren().add(AB);
        Line BC = new Line(v.get(2), v.get(3), v.get(4), v.get(5));
        BC.setStrokeWidth(patrulaterV.getComboBox().getSelectionModel().getSelectedItem());
        BC.setStroke(patrulaterV.getColorPicker().getValue());
        patrulaterV.getPane().getChildren().add(BC);
        Line CD = new Line(v.get(4), v.get(5), v.get(6), v.get(7));
        CD.setStrokeWidth(patrulaterV.getComboBox().getSelectionModel().getSelectedItem());
        CD.setStroke(patrulaterV.getColorPicker().getValue());
        patrulaterV.getPane().getChildren().add(CD);
        Line DA = new Line(v.get(6), v.get(7), v.get(0), v.get(1));
        DA.setStrokeWidth(patrulaterV.getComboBox().getSelectionModel().getSelectedItem());
        DA.setStroke(patrulaterV.getColorPicker().getValue());
        patrulaterV.getPane().getChildren().add(DA);
        Label label1 = new Label("A");
        label1.relocate(v.get(0) - 10, v.get(1) - 10);
        patrulaterV.getPane().getChildren().add(label1);
        Label label3 = new Label("B");
        label3.relocate(v.get(2) + 5, v.get(3) - 5);
        patrulaterV.getPane().getChildren().add(label3);
        Label label4 = new Label("C");
        label4.relocate(v.get(4) + 5, v.get(5) + 5);
        patrulaterV.getPane().getChildren().add(label4);
        Label label2 = new Label("D");
        label2.relocate(v.get(6) - 5, v.get(7) + 5);
        patrulaterV.getPane().getChildren().add(label2);
    }

    public void trapez() {
        if (this.puncte.size() == 0) {
            draw(patrulaterV.getTrapez());
            this.puncte = patrulaterV.getTrapez();
            count = 4;
        }
    }

    public void patrat() {
        if (this.puncte.size() == 0) {
            draw(patrulaterV.getPatrat());
            this.puncte = patrulaterV.getPatrat();
            count = 4;
        }
    }

    public void citireXML() {
        if (this.puncte.size() == 0) {
            Fisier f = new Fisier();
            ArrayList<Double> cord = f.citire();
            draw(cord);
            this.puncte = cord;
            count = 4;
        }
    }

    public void scriereXML() {
        if (this.puncte.size() > 0) {
            Fisier f = new Fisier();
            Patrulater p = setPatrulater(this.puncte);
            f.scriere(p);
        }
    }

    public void stergere() {
        if (this.puncte.size() > 0) {
            patrulaterV.getPane().getChildren().clear();
            this.puncte.clear();
            count = 0;
        }
    }

}
