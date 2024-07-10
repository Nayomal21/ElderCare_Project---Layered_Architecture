package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import lk.ijse.Launcher;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.RegisterUserBO;
import lk.ijse.bo.impl.RegisterUserBOImpl;
import lk.ijse.dto.UserDTO;



import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegistrationFormController implements Initializable {
    public TextField txtUserId;
    public TextField txtPw;
    public TextField txtName;
    private AnchorPane rootNode;
    @FXML
    private ChoiceBox<String> choiceBox;
    private  boolean AccesGiven= false;
    private final String[]  userType = {"Owner","Manager","Clerk"};
    RegisterUserBO registerUserBO = (RegisterUserBO) BOFactory.getBOFactory().getBO(BOFactory.BOTYPES.REGISTER);

    public void btnRegisterOnAction(ActionEvent actionEvent) {

        if (AccesGiven){
            try {
                boolean isSaved = registerUserBO.addUser(new UserDTO(txtUserId.getText(), txtName.getText(), txtPw.getText(), choiceBox.getValue()));
           if (isSaved) {
               new Alert(Alert.AlertType.CONFIRMATION, "new User Added!").show();
           btnBackOnAction(actionEvent);

           }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else  createDialog();



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll(userType);


    }

    public void setRootNode(AnchorPane leafNode) {        this.rootNode = rootNode;
    }

    public void btnBackOnAction(ActionEvent actionEvent)  {
        Stage stage = Launcher.stage;

        try {
            stage.setScene(new Scene(FXMLLoader
                    .load(this.getClass().getResource("/view/login_form2.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Login Form");
        stage.centerOnScreen();
        stage.show();
    }

    private void createDialog() {
        Dialog dialog = new Dialog();
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.getDialogPane().setContent(createLogForm(dialog));
        dialog.setTitle("Access Denied  ");
        dialog.setHeaderText("Before Register You have to \nget access to create user account. ");


        Image image = new Image("file:/home/nayomal/Desktop/projects/Eldercare/src/main/resources/icon/icon/ElderAdd_pic/alert.png");

        ImageView imageView = new ImageView(image);

        dialog.setGraphic(imageView);

        dialog.show();

    }

    private Node createLogForm(Dialog dialog) {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(7);
        gridPane.setHgap(5);
        // gridPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#FDA7DF"), CornerRadii.EMPTY, Insets.EMPTY)));

        Stop stop1 = new Stop(0, Color.valueOf("#25CCF7"));

        Stop stop2 = new Stop(1, Color.valueOf("#3B3B98"));

        LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stop1, stop2);

        BackgroundFill backgroundFill = new BackgroundFill(linearGradient, CornerRadii.EMPTY, Insets.EMPTY);


        // Set the background of the GridPane

        gridPane.setBackground(new Background(backgroundFill));

        Label lblUserName = new Label("Username:");
        lblUserName.setFont(Font.font("DejaVu Serif Condensed", FontWeight.BOLD,13));
        gridPane.add((lblUserName),0,0);
        TextField username = new TextField();
        PasswordField password = new PasswordField();
        gridPane.add((username),1,0);
        Label lblPassword = new Label("Password:");
        lblPassword.setFont(Font.font("DejaVu Serif Condensed", FontWeight.BOLD,13));
        username.setFont(Font.font("DejaVu Serif Condensed",13));

        gridPane.add(lblPassword,0,3);
        gridPane.add(password,1,3);
        Button loginAsManager = new Button("Give access ");
        loginAsManager.setFont(Font.font("DejaVu Serif Condensed", FontWeight.BOLD,13));
        loginAsManager.setStyle("-fx-background-color: #5dea3b; -fx-text-fill: #0a0a0a;");

        loginAsManager.setOnAction(event ->
        {
            try {
                String userLevel = registerUserBO.GiveAccesToCreateAccount(new UserDTO(username.getText(),password.getText()));
                if (userLevel.equals("Manager") || userLevel.equals("Owner")){
                 AccesGiven = true;
                 dialog.close();
                }else  new Alert(Alert.AlertType.INFORMATION,"Your User Level hasn't power to create account").show();

            } catch (Exception e) {
                System.out.println("op");
                throw new RuntimeException(e);
            }


        });

        gridPane.add((loginAsManager),0,4);
        return gridPane;

    }
}
