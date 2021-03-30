package model;

public class Dreapta extends Patrulater {
    Punct A;
    Punct B;

    public Dreapta(Punct a, Punct b) {
        A = a;
        B = b;
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
}
