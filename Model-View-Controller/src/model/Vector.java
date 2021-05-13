package model;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Vector {
    private double x;
    private double y;
    private double z;

    public Vector(double x, double y,double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) { this.x = x; }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public static Vector produsVectorial(Vector v, Vector w){
        Vector c = new Vector(0,0,0);
        c.setX(v.getY()*w.getZ() - v.getZ()*w.getY());
        c.setY(v.getZ()*w.getX() - v.getX()*w.getZ());
        c.setZ(v.getX()*w.getY() - v.getY()*w.getX());
        return c;
    }

    public static double unghiulDintreDoiVectori(Vector u, Vector v) {
        Double lungimeU = sqrt(pow(u.getX(), 2) + pow(u.getY(), 2));
        Double lungimeV = sqrt(pow(v.getX(), 2) + pow(v.getY(), 2));
        Double uv = u.getX() * v.getX() + u.getY() * v.getY();
        Double cos = uv / (lungimeU * lungimeV);
        Double arccos = Math.toDegrees(Math.acos(cos));
        return arccos;
    }


}





















