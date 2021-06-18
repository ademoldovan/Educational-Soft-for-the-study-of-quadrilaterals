package model;

import controller.Controller;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import java.util.*;

public class Limba extends Observable{

    private static final ObjectProperty<Locale> locale;
    private static ResourceBundle bundle;

    private static final Locale romana = new Locale( "RO");
    private static final Locale engleza = new Locale( "EN");
    private static final Locale franceza = new Locale( "FR");
    private static final Locale spaniola = new Locale( "ES");

    private static ObserverLimba observerLimba;

    public Limba() {
    }

    public ObserverLimba getObserverLimba() {
        return observerLimba;
    }

    public static void setObserverLimba(ObserverLimba observerLimba) {
        Limba.observerLimba = observerLimba;
    }

    static {
        locale = new SimpleObjectProperty<>(romana);
        bundle = ResourceBundle.getBundle("Model.limba", romana);
    }

    public static ResourceBundle getBundle() {
        return bundle;
    }

    public static void setBundle(ResourceBundle resourceBundle1) {
        bundle = resourceBundle1;
    }

    public void setLocale(Locale locale1, Controller controller) {
        locale.set(locale1);
        Locale.setDefault(locale1);
        ArrayList<Object> arg = new ArrayList<>();
        arg.add(locale1);
        arg.add(ResourceBundle.getBundle("Model.limba", locale1));
        observerLimba.update(this,arg);
    }

}
