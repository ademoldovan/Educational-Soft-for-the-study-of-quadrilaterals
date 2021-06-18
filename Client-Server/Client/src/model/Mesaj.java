package model;

import client.Client;
import java.io.*;
import java.util.ArrayList;

public class Mesaj implements IMesaj{

    public void trimiteMesaj(String mesaj, ArrayList<Double> puncte) throws IOException {
        OutputStream outputStream = Client.socket.getOutputStream();
        DataOutputStream objectOutputStream = new DataOutputStream(outputStream);
        mesaj  = mesaj + " " + puncte.get(0) + " " + puncte.get(1)  + " " + puncte.get(2)  + " " + puncte.get(3) +
                " " + puncte.get(4) + " " + puncte.get(5)  + " " + puncte.get(6)  + " " + puncte.get(7);
        System.out.println("Mesaj trimis: " + mesaj);
        objectOutputStream.writeUTF(mesaj);
    }

    String primesteMesaj() throws IOException {
        InputStream inputStream = Client.socket.getInputStream();
        DataInputStream objectInputStream = new DataInputStream(inputStream);
        String mes = (String) objectInputStream.readUTF();
        System.out.println("Mesaj primit: " + mes);
        return mes;
    }

    @Override
    public boolean concavSauConvex(ArrayList<Double> puncte) throws IOException {
        trimiteMesaj("concavSauConvex",puncte);
        String mes = primesteMesaj();
        String[] arrOfStr = mes.split(" ");
        boolean value = false;
        if(arrOfStr[0].equals("concavSauConvex")) {
            value = Boolean.parseBoolean(arrOfStr[1]);
        }
        return value;
    }

    @Override
    public boolean inscriptibil(ArrayList<Double> puncte) throws IOException {
        trimiteMesaj("inscriptibil", puncte);
        String ins = primesteMesaj();
        String[] arrOfStr1 = ins.split(" ");
        boolean inscriptibil = false;
        if(arrOfStr1[0].equals("inscriptibil")) {
            inscriptibil = Boolean.parseBoolean(arrOfStr1[1]);
        }
        return  inscriptibil;
    }

    @Override
    public boolean circumscriptibil( ArrayList<Double> puncte) throws IOException {
        trimiteMesaj("circumscriptibil", puncte);
        String ins = primesteMesaj();
        String[] arrOfStr1 = ins.split(" ");
        boolean circumscriptibil = false;
        if(arrOfStr1[0].equals("circumscriptibil")) {
            circumscriptibil = Boolean.parseBoolean(arrOfStr1[1]);
        }
        return circumscriptibil;
    }

    @Override
    public void salvareInFisier(ArrayList<Double> puncte) throws IOException {
        trimiteMesaj("scriereInFisier",puncte);
    }

    @Override
    public ArrayList<Double> citireDinFisier(ArrayList<Double> puncte) throws IOException {
        OutputStream outputStream = Client.socket.getOutputStream();
        DataOutputStream objectOutputStream = new DataOutputStream(outputStream);
        objectOutputStream.writeUTF("citireDinFisier");
        ArrayList<Double> cord = new ArrayList<>();
        String mes = primesteMesaj();
        String[] arrOfStr = mes.split(" ");
        if(arrOfStr[0].equals("citireDinFisier")) {
            for(int i = 1; i<9; i++){
                cord.add(Double.parseDouble(arrOfStr[i]));
            }
        }
        return cord;
    }

    @Override
    public ArrayList<Double> lungimileLAturilor(ArrayList<Double> puncte) throws IOException {
        ArrayList<Double> laturi = new ArrayList<>();
        trimiteMesaj("lungimileLaturilor",puncte);
        String mes = primesteMesaj();
        String[] arrOfStr = mes.split(" ");
        if(arrOfStr[0].equals("lungimileLaturilor")) {
            for(int i = 1; i<5; i++){
                laturi.add(Double.parseDouble(arrOfStr[i]));
            }
        }
        return laturi;
    }

    @Override
    public ArrayList<Double> masurileUnghiurilor(ArrayList<Double> puncte) throws IOException {
        ArrayList<Double> unghi = new ArrayList<>();
        trimiteMesaj("masurileUnghiurilor",puncte);
        String mes = primesteMesaj();
        String[] arrOfStr = mes.split(" ");
        if(arrOfStr[0].equals("masurileUnghiurilor")) {
            for(int i = 1; i<5; i++){
                unghi.add(Double.parseDouble(arrOfStr[i]));
            }
        }
        return unghi;
    }

    @Override
    public ArrayList<Double> razaCerculuiCircumscris(ArrayList<Double> puncte) throws IOException {
        ArrayList<Double> r = new ArrayList<>();
        trimiteMesaj("razaCerculuiCircumscris",puncte);
        String mes = primesteMesaj();
        String[] arrOfStr = mes.split(" ");
        if(arrOfStr[0].equals("razaCerculuiCircumscris")) {
            for(int i = 1; i<4; i++){
                r.add(Double.parseDouble(arrOfStr[i]));
            }
        }
        return r;
    }

    @Override
    public ArrayList<Double> bimediane(ArrayList<Double> puncte) throws IOException {
        ArrayList<Double> bimediane = new ArrayList<>();
        trimiteMesaj("bimediane",puncte);
        String mes = primesteMesaj();
        String[] arrOfStr = mes.split(" ");
        if(arrOfStr[0].equals("bimediane")) {
            for(int i = 1; i<9; i++){
                bimediane.add(Double.parseDouble(arrOfStr[i]));
            }
        }
        return bimediane;
    }

    @Override
    public double aria(ArrayList<Double> puncte) throws IOException {
        double aria= 0.0 ;
        trimiteMesaj("aria",puncte);
        String mes = primesteMesaj();
        String[] arrOfStr = mes.split(" ");
        if(arrOfStr[0].equals("aria")) {
            aria = Double.parseDouble(arrOfStr[1]);
        }
        return aria;
    }

    @Override
    public double perimetru(ArrayList<Double> puncte) throws IOException {
        double perim= 0.0 ;
        trimiteMesaj("perimetru",puncte);
        String mes = primesteMesaj();
        String[] arrOfStr = mes.split(" ");
        if(arrOfStr[0].equals("perimetru")) {
            perim = Double.parseDouble(arrOfStr[1]);
        }
        return perim;
    }


}
