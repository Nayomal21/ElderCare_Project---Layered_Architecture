package lk.ijse.controller;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.Launcher;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserBO;
import lk.ijse.bo.impl.UserBOImpl;
import lk.ijse.dto.UserDTO;



import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    public  static  String user;
    public  static  String userLevel="Manager";
    public AnchorPane leafNode;
    private AnchorPane rootNode;
    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    UserBO userBO = (UserBO) BOFactory.getBOFactory().getBO(BOFactory.BOTYPES.USER);
public  void  initialize(){
txtUserName.requestFocus();
}
    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {

        String userName = txtUserName.getText();
        String pw = txtPassword.getText();

        try {
            Boolean result = userBO.checkCrendials(new UserDTO(userName,pw));
            if (result){
              //  new Alert(Alert.AlertType.CONFIRMATION,"login SuccesFull").show();

               Stage  stage = Launcher.stage;
                    user=userName;
                stage.setScene(new Scene(FXMLLoader
                        .load(this.getClass().getResource("/view/dashBoard_form.fxml"))));
                stage.setTitle("Login Form");
                stage.centerOnScreen();
                stage.show();

            }
        } catch (SQLException e) {

            Image image = new Image("file:/home/nayomal/Desktop/projects/Eldercare/src/main/resources/icon/icon/ElderAdd_pic/alert.png");

            ImageView imageView = new ImageView(image);

            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.setGraphic(imageView);
            alert.show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }



    @FXML
    void linkRegistrationOnAction(ActionEvent event) {
        try {

            // Load the registration_form.fxml file

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/registration_form.fxml"));

            Pane registrationForm = loader.load();


            // Set the registration form as the child of the leafNode

            leafNode.getChildren().clear();

            leafNode.getChildren().add(registrationForm);


            // Get the controller of the registration form and set the root node

            RegistrationFormController registrationFormController = loader.getController();

            registrationFormController.setRootNode(leafNode);


        } catch (Exception e) {

            new Alert(Alert.AlertType.ERROR, "Error loading registration form: " + e.getMessage()).show();

        }


    }
    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }   @FXML
    void txtPasswordOnaction(ActionEvent event) {
        try {
            btnLoginOnAction(event);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Log in unsuccessful\n"+e.getMessage()).show();
        }
    }
}
