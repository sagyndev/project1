package sample;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.text.Position;
import java.awt.*;
import java.util.Map;

public class Tank extends Application {
    Position position;
    Map map;

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane pane = new GridPane();
        primaryStage.setTitle("World of Tanks");
        primaryStage.setScene(new Scene(pane, 1000, 600, Color.BLACK));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

