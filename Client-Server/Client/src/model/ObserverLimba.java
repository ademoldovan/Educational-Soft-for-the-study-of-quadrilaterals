package model;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;

public class ObserverLimba implements Observer {

    private final Controller controller;

    public ObserverLimba(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void update(Observable o, Object arg) {
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList = (ArrayList<Object>) arg;
        Locale locale1 = (Locale) arrayList.get(0);
        ResourceBundle bundle = (ResourceBundle) arrayList.get(1);

        Stage stage = controller.getStage();
        stage.hide();
        FXMLLoader loader = new FXMLLoader(controller.getClass().getClassLoader().getResource("View/sample.fxml"));
        bundle = ResourceBundle.getBundle("Model.limba",locale1);
        Limba.setBundle(bundle);
        loader.setResources(bundle);
        Parent root;
        try {
            root = loader.load();
            ((Controller)loader.getController()).setStage(stage);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
