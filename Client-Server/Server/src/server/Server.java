package server;

import model.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) throws IOException{
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("ServerSocket awaiting connections...");
        Patrulater p = Patrulater.getInstance();
        String[] arrOfStr;
        while(true){
            try {
                Socket socket = ss.accept();
                System.out.println("Connection from " + socket + "!");
                InputStream inputStream = socket.getInputStream();
                DataInputStream objectInputStream = new DataInputStream(inputStream);

                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream objectOutputStream = new DataOutputStream(outputStream);

                while (true) {
                    String mes = (String) objectInputStream.readUTF();
                    arrOfStr = mes.split(" ");
                    switch(arrOfStr[0]) {
                        case "concavSauConvex":
                            p = p.setPatrulater(arrOfStr);
                            boolean value = p.concavSauConvex(p);
                            String trimite = arrOfStr[0] + " " + Boolean.toString(value);
                            objectOutputStream.writeUTF(trimite);
                            break;
                        case "inscriptibil":
                            p = p.setPatrulater(arrOfStr);
                            boolean value1 = p.inscriptibil(p);
                            String trimite1 = arrOfStr[0] + " " + Boolean.toString(value1);
                            objectOutputStream.writeUTF(trimite1);
                            break;
                        case "circumscriptibil":
                            p = p.setPatrulater(arrOfStr);
                            boolean value2 = p.circumscriptibil(p);
                            String trimite2 = arrOfStr[0] + " " + Boolean.toString(value2);
                            objectOutputStream.writeUTF(trimite2);
                            break;
                        case "scriereInFisier":
                            p = p.setPatrulater(arrOfStr);
                            Fisier f = new Fisier();
                            f.scriere(p);
                            break;
                        case "citireDinFisier":
                            Fisier f1 = new Fisier();
                            ArrayList<Double> puncte = f1.citire();
                            String mesaj  = arrOfStr[0] + " " + puncte.get(0) + " " + puncte.get(1)  + " " + puncte.get(2)  + " " + puncte.get(3) +
                                    " " + puncte.get(4) + " " + puncte.get(5)  + " " + puncte.get(6)  + " " + puncte.get(7);
                            objectOutputStream.writeUTF(mesaj);
                            break;
                        case "lungimileLaturilor":
                            p = p.setPatrulater(arrOfStr);
                            ArrayList<Double> lungimi = p.lungimileLaturilor(p);
                            String trimite3 = arrOfStr[0] + " " + Double.toString(lungimi.get(0)) + " " + Double.toString(lungimi.get(1)) + " " +
                                    Double.toString(lungimi.get(2)) + " " + Double.toString(lungimi.get(3));
                            objectOutputStream.writeUTF(trimite3);
                            break;
                        case "masurileUnghiurilor":
                            p = p.setPatrulater(arrOfStr);
                            ArrayList<Double> unghiuri = p.masurileUnghiurilor(p);
                            String trimite4 = arrOfStr[0] + " " + Double.toString(unghiuri.get(0)) + " " + Double.toString(unghiuri.get(1)) + " " +
                                    Double.toString(unghiuri.get(2)) + " " + Double.toString(unghiuri.get(3));
                            objectOutputStream.writeUTF(trimite4);
                            break;
                        case "aria":
                            p = p.setPatrulater(arrOfStr);
                            double aria = p.aria(p);
                            String trimite5 = arrOfStr[0] + " " + aria;
                            objectOutputStream.writeUTF(trimite5);
                            break;
                        case "perimetru":
                            p = p.setPatrulater(arrOfStr);
                            double perimetru = p.perimetru(p);
                            String trimite6 = arrOfStr[0] + " " + perimetru;
                            objectOutputStream.writeUTF(trimite6);
                            break;
                        case "razaCerculuiCircumscris":
                            p = p.setPatrulater(arrOfStr);
                            Cerc c = p.cerculCircumscris(p);
                            ArrayList<Double> cerc = new ArrayList<>();
                            cerc.add(c.getCentru().getX());
                            cerc.add(c.getCentru().getY());
                            cerc.add(c.getRaza());
                            String trimite7 = arrOfStr[0] + " " + Double.toString(cerc.get(0)) + " " + Double.toString(cerc.get(1)) + " " + Double.toString(cerc.get(2)) ;
                            objectOutputStream.writeUTF(trimite7);
                            break;
                        case "bimediane":
                            p = p.setPatrulater(arrOfStr);
                            ArrayList<Dreapta> bimediane = p.bimediane(p);
                            String mesaj1  = arrOfStr[0] + " " + bimediane.get(0).getA().getX() + " " + bimediane.get(0).getA().getY() + " "
                                                               + bimediane.get(0).getB().getX() + " " + bimediane.get(0).getB().getY() + " "
                                                               + bimediane.get(1).getA().getX() + " " + bimediane.get(1).getA().getY() + " "
                                                               + bimediane.get(1).getB().getX() + " " + bimediane.get(1).getB().getY();
                            objectOutputStream.writeUTF(mesaj1);
                            break;
                        default:
                    }
                }
            }catch(IOException e){
                System.out.println("Deconectat de la client!");
            }
        }
    }
}
