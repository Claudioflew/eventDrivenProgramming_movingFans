// Student name: Koichi Nakata (ID: knakata595)

package org.example.assignment12;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class Assignment12 extends Application {
    private double increment = 1;

    public BorderPane getPane(double radius) {
        BorderPane pane = new BorderPane();
        Pane fanPane = new Pane();

        Circle circle = new Circle(radius, radius, radius);
        circle.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
        circle.setFill(null);
        fanPane.getChildren().add(circle);

        double arcRad = radius * 0.95;

        ArrayList<Arc> arcList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Arc arc = new Arc(radius, radius, arcRad, arcRad, 40 + i*90, 40);
            arc.setType(ArcType.ROUND);
            arc.setFill(Color.color(Math.random(), Math.random(), Math.random()));

            arcList.add(arc);
            fanPane.getChildren().add(arc);
        }

        VBox fanBox = new VBox(fanPane); // Just want to layout properly
        fanBox.setAlignment(Pos.CENTER);
        fanBox.setPadding(new Insets(10, 10, 10, 10));
        pane.setCenter(fanBox);

        EventHandler<ActionEvent> moveFans = e -> {
            for (Arc arc : arcList) {
                arc.setStartAngle(arc.getStartAngle() + increment);
            }
        };

        Timeline movement = new Timeline(new KeyFrame(Duration.millis(50), moveFans));
        movement.setCycleCount(Timeline.INDEFINITE);
        movement.play();

        Button btPlayPause = new Button("Play/Pause");
        Button btReverse = new Button("Reverse");
        Button btSpeedUp = new Button("Speed Up");
        Button btSlowDown = new Button("Slow Down");

        HBox paneForButtons = new HBox(10);
        paneForButtons.getChildren().addAll(btPlayPause, btReverse, btSpeedUp, btSlowDown);
        paneForButtons.setAlignment(Pos.CENTER);
        paneForButtons.setPadding(new Insets(10, 10, 10, 10));

        pane.setBottom(paneForButtons);

        btPlayPause.setOnAction(e -> {
            if (movement.getStatus() == Animation.Status.RUNNING) movement.pause();
            else movement.play();
        });

        btReverse.setOnAction(e -> increment *= -1);
        btSpeedUp.setOnAction(e -> increment *= 2);
        btSlowDown.setOnAction(e -> increment /= 2);

        return pane;
    }

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(getPane(200), 420, 460);
        stage.setTitle("Assignment12");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}