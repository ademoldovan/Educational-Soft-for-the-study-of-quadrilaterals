package model;

import java.util.ArrayList;
import static java.lang.Math.*;

public class Patrulater{

    private static Patrulater patrulater;

    private Punct A;
    private Punct B;
    private Punct C;
    private Punct D;
    private Patrulater() {

    }

    public static Patrulater getInstance()
    {
        if (patrulater==null)
            patrulater = new Patrulater();
        return patrulater;
    }

    public Punct getA() {
        return A;
    }

    public void setA(Punct a) {
        A = a;
    }

    public Punct getB() {
        return B;
    }

    public void setB(Punct b) {
        B = b;
    }

    public Punct getC() {
        return C;
    }

    public void setC(Punct c) {
        C = c;
    }

    public Punct getD() {
        return D;
    }

    public void setD(Punct d) {
        D = d;
    }

    public Patrulater setPatrulater(String[] arrOfStr){
        Patrulater p = Patrulater.getInstance();
        p.setA(new Punct(Double.parseDouble(arrOfStr[1]), Double.parseDouble(arrOfStr[2])));
        p.setB(new Punct(Double.parseDouble(arrOfStr[3]), Double.parseDouble(arrOfStr[4])));
        p.setC(new Punct(Double.parseDouble(arrOfStr[5]), Double.parseDouble(arrOfStr[6])));
        p.setD(new Punct(Double.parseDouble(arrOfStr[7]), Double.parseDouble(arrOfStr[8])));
        return p;
    }

    public ArrayList<Double> lungimileLaturilor(Patrulater p) {
        double AB = sqrt(pow((p.getB().getX() - p.getA().getX()), 2) + pow((p.getB().getY() - p.getA().getY()), 2));
        double BC = sqrt(pow((p.getC().getX() - p.getB().getX()), 2) + pow((p.getC().getY() - p.getB().getY()), 2));
        double CD = sqrt(pow((p.getD().getX() - p.getC().getX()), 2) + pow((p.getD().getY() - p.getC().getY()), 2));
        double DA = sqrt(pow((p.getA().getX() - p.getD().getX()), 2) + pow((p.getA().getY() - p.getD().getY()), 2));
        ArrayList<Double> lungimeLaturi = new ArrayList<Double>();
        lungimeLaturi.add(AB);
        lungimeLaturi.add(BC);
        lungimeLaturi.add(CD);
        lungimeLaturi.add(DA);
        return lungimeLaturi;
    }

    public ArrayList<Double> masurileUnghiurilor(Patrulater p) {
        //calculam vectorii laturilor
        Vector ab = new Vector(p.getB().getX() - p.getA().getX(), p.getB().getY() - p.getA().getY(), 0);
        Vector bc = new Vector(p.getC().getX() - p.getB().getX(), p.getC().getY() - p.getB().getY(), 0);
        Vector cd = new Vector(p.getD().getX() - p.getC().getX(), p.getD().getY() - p.getC().getY(), 0);
        Vector da = new Vector(p.getA().getX() - p.getD().getX(), p.getA().getY() - p.getD().getY(), 0);
        Vector ad = new Vector(p.getD().getX() - p.getA().getX(), p.getD().getY() - p.getA().getY(), 0);
        Vector ba = new Vector(p.getA().getX() - p.getB().getX(), p.getA().getY() - p.getB().getY(), 0);
        Vector cb = new Vector(p.getB().getX() - p.getC().getX(), p.getB().getY() - p.getC().getY(), 0);
        Vector dc = new Vector(p.getC().getX() - p.getD().getX(), p.getC().getY() - p.getD().getY(), 0);

        double abc = Vector.unghiulDintreDoiVectori(ba, bc);
        double bcd = Vector.unghiulDintreDoiVectori(cb, cd);
        double cda = Vector.unghiulDintreDoiVectori(dc, da);
        double dab = Vector.unghiulDintreDoiVectori(ab, ad);

        ArrayList<Double> unghiuri = new ArrayList<>();
        unghiuri.add(dab);
        unghiuri.add(abc);
        unghiuri.add(bcd);
        if (!concavSauConvex(p))
            unghiuri.add(360 - cda);
        unghiuri.add(cda);
        return unghiuri;
    }

    public boolean concavSauConvex(Patrulater p) {
        //true => convex, false => concav
        //calculam vectorii laturilor

        Vector ab = new Vector(p.getB().getX() - p.getA().getX(), p.getB().getY() - p.getA().getY(), 0);
        Vector bc = new Vector(p.getC().getX() - p.getB().getX(), p.getC().getY() - p.getB().getY(), 0);
        Vector cd = new Vector(p.getD().getX() - p.getC().getX(), p.getD().getY() - p.getC().getY(), 0);
        Vector da = new Vector(p.getA().getX() - p.getD().getX(), p.getA().getY() - p.getD().getY(), 0);
        //calcul produs vectorial al laturilor
        Vector p1 = Vector.produsVectorial(ab, bc);
        Vector p2 = Vector.produsVectorial(bc, cd);
        Vector p3 = Vector.produsVectorial(cd, da);
        Vector p4 = Vector.produsVectorial(da, ab);

        return p1.getZ() >= 0 && p2.getZ() >= 0 && p3.getZ() >= 0 && p4.getZ() >= 0;
    }

    public double aria(Patrulater p) {
        ArrayList<Double> laturi = this.lungimileLaturilor(p);
        double diag = sqrt(pow(p.getA().getX() - p.getC().getX(), 2) + pow(p.getA().getY() - p.getC().getY(), 2));
        double s = (laturi.get(0) + laturi.get(1) + diag) / 2;
        double a1 = sqrt(s * (s - laturi.get(0)) * (s - laturi.get(1)) * (s - diag));
        double s2 = (laturi.get(2) + laturi.get(3) + diag) / 2;
        double a2 = sqrt(s2 * (s2 - laturi.get(2)) * (s2 - laturi.get(3)) * (s2 - diag));
        return a1 + a2;
    }

    public double perimetru(Patrulater p) {
        ArrayList<Double> lungimeLaturi = lungimileLaturilor(p);
        double perim = 0;
        for (Double i : lungimeLaturi) {
            perim += i;
        }
        return perim;
    }

    public boolean inscriptibil(Patrulater p) {
        ArrayList<Double> unghiuri = new ArrayList<>();
        unghiuri = masurileUnghiurilor(p);
        if (unghiuri.get(0) + unghiuri.get(2) == unghiuri.get(1) + unghiuri.get(3)) {
            return true;
        }
        return false;
    }

    public boolean circumscriptibil(Patrulater p) {
        ArrayList<Double> lungimiLaturi = lungimileLaturilor(p);
        if (lungimiLaturi.get(0) + lungimiLaturi.get(2) == lungimiLaturi.get(1) + lungimiLaturi.get(3)) {
            return true;
        }
        return false;
    }

    public ArrayList<Dreapta> bimediane(Patrulater p) {
        ArrayList<Dreapta> bimediane = new ArrayList<>();
        bimediane.add(new Dreapta(new Punct((p.getA().getX() + p.getB().getX()) / 2, (p.getA().getY() + p.getB().getY()) / 2), new Punct((p.getC().getX() + p.getD().getX()) / 2, (p.getC().getY() + p.getD().getY()) / 2)));
        bimediane.add(new Dreapta(new Punct((p.getB().getX() + p.getC().getX()) / 2, (p.getB().getY() + p.getC().getY()) / 2), new Punct((p.getD().getX() + p.getA().getX()) / 2, (p.getD().getY() + p.getA().getY()) / 2)));
        return bimediane;
    }

    public void bisectoare(Patrulater p){

    }

    public Cerc cerculCircumscris(Patrulater p) {
        double a = p.getA().getX() - p.getB().getX();
        double b = p.getA().getY() - p.getB().getY();
        double d = p.getA().getX() - p.getC().getX();
        double e = p.getA().getY() - p.getC().getY();
        double c = (pow(p.getB().getX(), 2) + pow(p.getB().getY(), 2) - pow(p.getA().getX(), 2) - pow(p.getA().getY(), 2)) / 2;
        double f = (pow(p.getC().getX(), 2) + pow(p.getC().getY(), 2) - pow(p.getA().getX(), 2) - pow(p.getA().getY(), 2)) / 2;
        double yc = abs((a * f - d * c) / (a * e - d * b));
        double xc = abs((c - b * yc) / a);
        double r = sqrt(pow(p.getA().getX() - xc, 2) + pow(p.getA().getY() - yc, 2));
        Cerc cerc = new Cerc(new Punct(xc, yc), r);
        return cerc;
    }

    public void cerculInscris(Patrulater p) {
        ArrayList<Dreapta> bimediane = bimediane(p);
        //Cerc cerc = new Cerc(new Punct(bimediane.get(0), bimediane.get(0)), );
       //return cerc;
    }

}















