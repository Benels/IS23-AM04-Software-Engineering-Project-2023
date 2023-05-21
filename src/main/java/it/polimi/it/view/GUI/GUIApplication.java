package it.polimi.it.view.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIApplication extends Application {

    //private GameStartController gameStartController;

    /*
    public GUIApplication(){
        this.gameStartController = new GameStartController(this);
    }

     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIApplication.class.getResource("/GameStart.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("My Shelfie");
        stage.setScene(scene);
        stage.setResizable(false); //----> volendo si può evitare che la finestra venga ingrandita
        //maximize(stage,16/9,true); --->da capire ancora come si fa
        stage.show();
    }

    /*
    private void maximize(Stage stage, double ratio, Boolean keepSquare){
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set Stage boundaries to visible bounds of the main screen
        double W = primaryScreenBounds.getWidth()/ratio;
        double H = primaryScreenBounds.getHeight()/ratio;

        if (keepSquare) {
            double min = Math.min(W, H);
            W = min;
            H = min;
        }

        stage.setWidth(W);
        stage.setHeight(H);

    }

     */

    public static void main(String[] args) {
        launch(args);
    }
}
