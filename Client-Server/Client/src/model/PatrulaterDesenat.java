package model;

import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class PatrulaterDesenat {

    private Line[] diagonale;
    private Line[] bimediane;
    private Line[] bisectoare;
    private Line razaCerculuiInscris;
    private Line razaCerculuiCircumscris;
    private Label[] litere;
    private Circle cercCircumscris;

    public PatrulaterDesenat(){
        this.bisectoare = new Line[2];
        this.bimediane = new Line[2];
        this.diagonale = new Line[2];
        this.litere = new Label[4];
    }

    public Line getRazaCerculuiInscris() {
        return razaCerculuiInscris;
    }

    public void setRazaCerculuiInscris(Line razaCerculuiInscris) {
        this.razaCerculuiInscris = razaCerculuiInscris;
    }

    public Line getRazaCerculuiCircumscris() {
        return razaCerculuiCircumscris;
    }

    public void setRazaCerculuiCircumscris(Line razaCerculuiCircumscris) {
        this.razaCerculuiCircumscris = razaCerculuiCircumscris;
    }

    public Line[] getDiagonale() {
        return diagonale;
    }

    public void setDiagonale(Line[] diagonale) {
        this.diagonale = diagonale;
    }

    public Line[] getBimediane() {
        return bimediane;
    }

    public void setBimediane(Line[] bimediane) {
        this.bimediane = bimediane;
    }

    public Line[] getBisectoare() {
        return bisectoare;
    }

    public void setBisectoare(Line[] bisectoare) {
        this.bisectoare = bisectoare;
    }

    public Label[] getLitere() {
        return litere;
    }

    public void setLitere(Label[] litere) {
        this.litere = litere;
    }

    public Circle getCercCircumscris() {
        return cercCircumscris;
    }

    public void setCercCircumscris(Circle cercCircumscris) {
        this.cercCircumscris = cercCircumscris;
    }
}
