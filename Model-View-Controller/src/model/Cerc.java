package model;

public class Cerc {
    private Punct centru;
    private double raza;

    public Cerc(Punct centru, double raza) {
        this.centru = centru;
        this.raza = raza;
    }

    public Punct getCentru() {
        return centru;
    }

    public void setCentru(Punct centru) {
        this.centru = centru;
    }

    public double getRaza() {
        return raza;
    }

    public void setRaza(double raza) {
        this.raza = raza;
    }
}
