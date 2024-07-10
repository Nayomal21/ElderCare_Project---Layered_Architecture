package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.ijse.Launcher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingFormController  implements Initializable {
    @FXML
    private ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        imageView.setImage(new Image("icon/icon/login_form_pic/logo-remove_background.png"));
        imageView.setCache(true);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(5000), actionEvent -> {
            System.out.println("Welcome to Cinnamon White Elder Home");
        });

        timeline.getKeyFrames().addAll(keyFrame1);
        timeline.playFromStart();

        timeline.setOnFinished(actionEvent -> {
            try {

                System.out.println("finished");
                Parent root = FXMLLoader.load(getClass().getResource("/view/login_form2.fxml"));
Launcher.stage.close();
             Stage   stage= new Stage();
                     Launcher.stage=stage;

                stage.setScene(new Scene(root));



                stage.centerOnScreen();

                stage.setResizable(false);
                Image image = new Image("icon/icon/login_form_pic/logo_design.png", 32, 32, false, false);

                stage.getIcons().add(image);
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
