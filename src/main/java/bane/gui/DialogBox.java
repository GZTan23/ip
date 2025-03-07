package bane.gui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    public DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        dialog.getStyleClass().add("reply-label");
    }

    public static ArrayList<DialogBox> getUserDialog(Image i, String... s) {
        ArrayList<DialogBox> dialogBoxes = new ArrayList<>();

        for (String dialog : s) {
            dialogBoxes.add(new DialogBox(dialog, i));
        }

        return dialogBoxes;
    }

    public static ArrayList<DialogBox> getBaneDialog(Image i, String... s) {
        ArrayList<DialogBox> dialogBoxes = new ArrayList<>();

        for (String dialog : s) {
            var db = new DialogBox(dialog, i);
            db.flip();
            dialogBoxes.add(db);
        }

        return dialogBoxes;
    }
}
