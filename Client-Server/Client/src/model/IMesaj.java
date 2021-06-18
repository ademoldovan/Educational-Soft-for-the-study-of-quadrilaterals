package model;

import java.io.IOException;
import java.util.ArrayList;

public interface IMesaj {

    boolean concavSauConvex( ArrayList<Double> puncte) throws IOException;

    boolean inscriptibil( ArrayList<Double> puncte) throws IOException;

    boolean circumscriptibil(ArrayList<Double> puncte) throws IOException;

    void salvareInFisier( ArrayList<Double> puncte) throws IOException;

    ArrayList<Double> citireDinFisier( ArrayList<Double> puncte) throws IOException;

    ArrayList<Double> lungimileLAturilor( ArrayList<Double> puncte) throws IOException;

    ArrayList<Double> masurileUnghiurilor( ArrayList<Double> puncte) throws IOException;

    ArrayList<Double> razaCerculuiCircumscris( ArrayList<Double> puncte) throws IOException;

    ArrayList<Double> bimediane(ArrayList<Double> puncte) throws IOException;

    double aria(ArrayList<Double> puncte) throws IOException;

    double perimetru(ArrayList<Double> puncte) throws IOException;

}
