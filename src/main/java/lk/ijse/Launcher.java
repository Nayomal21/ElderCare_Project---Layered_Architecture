package lk.ijse;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class Launcher extends Application {

    public   static Stage stage ;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {



        Launcher.stage =stage;

        try {

            Parent root = FXMLLoader.load(this.getClass().getResource("/view/Loading_form.fxml"));
            root.setStyle("-fx-background-color: transparent;");
            Scene scene = new Scene(root,Color.TRANSPARENT);

            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);


        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"form error - "+ e.getMessage()).show();
        }
       stage.setTitle("Login Form");
        stage.centerOnScreen();
        stage.show();
      /*  Thread thread = new Thread(() -> {
            try {
                FeeRepo.checkFeeCreattion(LocalDate.now());
            } catch (SQLException e) {
                Platform.runLater(() -> {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                    System.out.println(e.getMessage());
                });
            }
            try {
                SaveLogDetailRepo.save();
            } catch (SQLException e) {
                System.out.println("Already login saved for today");
            }
        });
        thread.start();
*/
    }
}
