package com.ozturk.ozturkmob;

import com.pi4j.io.gpio.*;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.util.Properties;


public class HelloApplication extends Application {

//    final GpioController gpio = GpioFactory.getInstance();
    private static final int PIN_BUTTON = 24; // PIN 18 = BCM 24
    private MediaPlayer mediaPlayer;
    private String[] videoUrls = {
            "C:/1.mp4",
            "C:/2.mp4",
            "C:/3.mp4"
    };
    private int currentVideoIndex = 0;
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {

        playVideo(videoUrls[currentVideoIndex], stage);

    }

    private void playVideo(String videoUrl, Stage stage) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }


        Media media = new Media(new File(videoUrl).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        final DoubleProperty width = mediaView.fitWidthProperty();
        final DoubleProperty height = mediaView.fitHeightProperty();

        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));


        Group root = new Group();
        root.getChildren().add(mediaView);
        stage.setScene(new Scene(root, 1800, 850));
        stage.getScene().setCursor(Cursor.NONE);
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();

        mediaPlayer.setOnEndOfMedia(() -> {
            currentVideoIndex = (currentVideoIndex + 1) % videoUrls.length;
            playVideo(videoUrls[currentVideoIndex], stage);
        });

        mediaPlayer.play();
    }

    private void gpioRead(){

        // Provision GPIO pin #01 as an input pin with internal pull-down resistor
//        final GpioPinDigitalInput myInputPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_01, PinPullResistance.PULL_DOWN);
//
//        // Read the state of the GPIO pin
//        if (myInputPin.isHigh()) {
//            System.out.println("Pin is HIGH");
//        } else {
//            System.out.println("Pin is LOW");
//        }
//
//        // Shutdown GPIO controller
//        gpio.shutdown();

    }

    public static void main(String[] args) {
        launch();
    }
}