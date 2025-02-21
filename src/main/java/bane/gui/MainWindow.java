package bane.gui;

import java.util.ArrayList;

import bane.core.Bane;
import bane.exception.StorageException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Bane bane;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image baneImage = new Image(this.getClass().getResourceAsStream("/images/bane.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Bane instance
     * */
    public void setBane(Bane b) {
        bane = b;
        try {
            String[] startReply = bane.run().toArray(new String[0]);

            dialogContainer.getChildren().addAll(DialogBox.getBaneDialog(baneImage, startReply));

        } catch (StorageException e) {
            String exceptionMessage = e.getMessage();
            dialogContainer.getChildren().addAll(DialogBox.getBaneDialog(baneImage, exceptionMessage));
            exitApplication();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bane's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bane.getResponse(input);
        ArrayList<DialogBox> userDialogBoxes = DialogBox.getUserDialog(userImage, input);
        ArrayList<DialogBox> baneDialogBoxes = DialogBox.getBaneDialog(baneImage, response);
        ArrayList<DialogBox> combinedDialogBoxes = new ArrayList<>(userDialogBoxes);
        combinedDialogBoxes.addAll(baneDialogBoxes);
        dialogContainer.getChildren().addAll(combinedDialogBoxes);

        userInput.clear();
        if (input.equals("bye")) {
            String message = "";
            try {
                message = bane.stop();
            } catch (StorageException e) {
                message = e.getMessage();
            } finally {

                ArrayList<DialogBox> baneDialog = DialogBox.getBaneDialog(baneImage, message);
                dialogContainer.getChildren().addAll(baneDialog);
                exitApplication();
            }
        }
    }

    @FXML
    private void exitApplication() {
        //@@author ypuppy-reused
        //Reused from https://github.com/nus-cs2103-AY2425S2/forum/issues/160
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> Platform.exit());
        pause.play();
        //@@author
    }

}
