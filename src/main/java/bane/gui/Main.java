package bane.gui;

import java.io.IOException;

import bane.core.Bane;
import bane.exception.StorageException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Bane using FXML.
 */
public class Main extends Application {

    private Bane bane = new Bane();
    private Image baneImage = new Image(this.getClass().getResourceAsStream("/images/bane.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Bane Chatbot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBane(bane); // inject the Bane instance
            stage.show();

            // Solution below adapted from
            // https://www.reddit.com/r/javahelp/comments/pdf8p7/stuck_on_making_a_are_you_sure_you_want_to_exit/
            stage.setOnCloseRequest(event -> {
                try {
                    bane.stop();
                } catch (StorageException e) {
                    DialogBox.getBaneDialog(baneImage, e.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
