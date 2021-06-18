package client;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class Client extends Application {

    public static Socket socket = null;

    @Override
    public void start(Stage stage) throws Exception {
        Locale locale = new Locale("RO");
        ResourceBundle bundle = ResourceBundle.getBundle("Model.limba", locale);
        URL url = new File("C:\\Users\\adeli\\Desktop\\PS\\PROIECT\\Proiect\\Client\\src\\view\\sample.fxml").toURI().toURL();
        FXMLLoader loader= new FXMLLoader(url,bundle);
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setStage(stage);
        Scene scene = new Scene(root);
        stage.setTitle("Studiul patrulaterului");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        // need host and port, we want to connect to the ServerSocket at port 7777
        socket = new Socket("localhost", 7777);
        System.out.println("Connected!");
        launch(args);
        System.out.println("Closing socket and terminating program.");
        socket.close();
    }

}
