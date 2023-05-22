package it.polimi.it.view.GUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;


import javafx.scene.control.TextField;
import java.io.IOException;

public class CreateGameViewController {

    private Stage stage;
    private String players;
    @FXML
    TextField NumOfPlayers;

    public void GotoGame(ActionEvent actionEvent) throws IOException {
        players = NumOfPlayers.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(GUIApplication.class.getResource("/Game.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("My Shelfie");
        stage.setScene(scene);
        stage.show();
    }
}