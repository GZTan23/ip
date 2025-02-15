package bane.gui;

import bane.core.Bane;
import bane.core.Ui;
import bane.exception.StorageException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
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
     * Injects the Duke instance
     * */
    public void setBane(Bane b) {
        bane = b;
        try {
            String startReply = bane.run();
            dialogContainer.getChildren().add(DialogBox.getBaneDialog(startReply, baneImage));

        } catch (StorageException e) {
            String exceptionMessage = e.getMessage();
            dialogContainer.getChildren().add(DialogBox.getBaneDialog(exceptionMessage, baneImage));
            exitApplication();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bane.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBaneDialog(response, baneImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            String message = "";
            try {
                message = bane.stop();
            } catch (StorageException e) {
                message = e.getMessage();
            } finally {

                DialogBox baneDialog = DialogBox.getBaneDialog(message, baneImage);
                dialogContainer.getChildren().add(baneDialog);
                exitApplication();
            }
        }
    }

    @FXML
    private void exitApplication() {
        //@@author ypuppy-reused
        //Reused from https://github.com/nus-cs2103-AY2425S2/forum/issues/160
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished( event -> Platform.exit());
        pause.play();
        //@@author
    }

}